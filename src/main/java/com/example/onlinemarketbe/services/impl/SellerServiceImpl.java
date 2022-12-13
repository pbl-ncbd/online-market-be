package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.common.enums.ERole;
import com.example.onlinemarketbe.common.exception.NotFoundException;
import com.example.onlinemarketbe.common.utils.ImageUrlHelper;
import com.example.onlinemarketbe.dto.*;
import com.example.onlinemarketbe.model.Identity;
import com.example.onlinemarketbe.model.IdentityImage;
import com.example.onlinemarketbe.model.Report;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.ReportRequest;
import com.example.onlinemarketbe.repositories.*;
import com.example.onlinemarketbe.services.ImgService;
import com.example.onlinemarketbe.services.SellerService;
import com.example.onlinemarketbe.common.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final RoleRepository roleRepository;
    private final ReportRepository reportRepository;
    private final IdentityImageRepository identityImageRepository;
    private final ModelMapper modelMapper;
    private final ImgService imgService;

    public SellerServiceImpl(UserRepository userRepository, IdentityRepository identityRepository, RoleRepository roleRepository, ReportRepository reportRepository, IdentityImageRepository identityImageRepository, ImgService imgService) {
        this.userRepository = userRepository;
        this.identityRepository = identityRepository;
        this.roleRepository = roleRepository;
        this.reportRepository = reportRepository;
        this.identityImageRepository = identityImageRepository;
        this.imgService = imgService;
        this.modelMapper = new ModelMapper();
        this.modelMapper.typeMap(IdentityImage.class, IdentityDto.IdentityImageDto.class).addMappings(mapper -> mapper.map(IdentityImage::getUrl, IdentityDto.IdentityImageDto::setUrl));
        this.modelMapper.typeMap(User.class, UserDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getInformation().getName(), UserDto::setInformationName);
            mapper.map(src -> src.getInformation().getPhone(), UserDto::setInformationPhone);
        }
        );
    }

    @Override
    public IdentityDto getSellerIdentity(int user_id) {
        var res = identityRepository.findById(user_id).orElseThrow(() -> new NotFoundException(String.format("User has id %d not found", user_id)));
        return modelMapper.map(res, IdentityDto.class);
    }

    @Override
    @Transactional
    public void createSeller(SellerRegisterDto sellerRegisterDto) {
        User user = userRepository.findById(sellerRegisterDto.getUserId())
                .orElseThrow(() -> new NotFoundException(String.format("User %s not found", sellerRegisterDto.getUserId())));
        Identity newIdentity;
        if(user.getIdentity() == null) {

            newIdentity = new Identity();
            newIdentity.setName(sellerRegisterDto.getName());
            newIdentity.setGender(sellerRegisterDto.getGender());
            newIdentity.setAddress(sellerRegisterDto.getAddress());
            newIdentity.setUser(user);
            user.setIdentity(newIdentity);
            newIdentity = identityRepository.save(newIdentity);
        }
        else {
            newIdentity = identityRepository.findByUser_Id(sellerRegisterDto.getUserId())
                    .orElseThrow(() -> new NotFoundException(String.format("Can't find identity of seller: %d", sellerRegisterDto.getUserId())));
            newIdentity.setName(sellerRegisterDto.getName());
            newIdentity.setGender(sellerRegisterDto.getGender());
            newIdentity.setAddress(sellerRegisterDto.getAddress());
            var idCardsList = newIdentity.getIdCards();
            idCardsList.forEach(i -> {
                var url = i.getUrl();
                identityImageRepository.deleteById(i.getId());
                imgService.deleteImg(i.getUrl());
            });
            newIdentity.getIdCards().clear();
        }

        Identity finalNewIdentity = newIdentity;
        Arrays.asList(sellerRegisterDto.getIdCards()).forEach(idCard -> {
            IdentityImage identityImage = new IdentityImage();
            identityImage.setIdentity(finalNewIdentity);
            identityImage.setUrl(imgService.uploadImg(idCard));
            identityImageRepository.save(identityImage);
            finalNewIdentity.getIdCards().add(identityImage);
        });
        identityRepository.save(finalNewIdentity);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void confirmSeller(int sellerId) {
        var seller = userRepository.findById(sellerId).orElseThrow(() -> new NotFoundException(String.format("User has id: %d not found",sellerId)));
        seller.getIdentity().setConfirm(true);
        seller.getRoles().add(roleRepository.findRoleByName(ERole.ROLE_SELLER));
        userRepository.save(seller);
    }

    @Override
    @Transactional
    public void reportSeller(int sellerId, ReportRequest reportRequest) {
        userRepository.findById(sellerId).orElseThrow(() -> new NotFoundException(String.format("User has id: %d not found",sellerId)));
        reportRepository.createReport(reportRequest.getReportId(), reportRequest.getReportedId(), reportRequest.getReason());
    }

    @Override
    @Transactional
    public void deleteSeller(int sellerId) {
        var seller = userRepository.findById(sellerId).orElseThrow(() -> new NotFoundException(String.format("User has id: %d not found",sellerId)));
        var identity = identityRepository.findByUser_Id(sellerId).orElseThrow(() -> new NotFoundException("Can't find identity of seller: "+sellerId));
        var imageList = seller.getIdentity().getIdCards().stream().map(IdentityImage::getUrl);
        seller.setIdentity(null);
        seller.setRoles(null);
        identityRepository.deleteById(identity.getId());
        userRepository.save(seller);
        imageList.forEach(i -> imgService.deleteImg(i));
    }

    @Override
    public List<ReportDto> getSellerReports(int page) {
        Pageable pageWithFiveElements = PageRequest.of(page, 5);
        List<Report> reportList = reportRepository.findAllReport(pageWithFiveElements);
        reportList.forEach(i -> log.info(i.getReport().toString()));
        return reportList.stream().map(i -> modelMapper.map(i, ReportDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<IdentityDto> getAllSellers() {
        var identity = identityRepository.findByDeletedFalse();
        if(identity==null) identity = new ArrayList<>();
        return identity.stream().map(i -> modelMapper.map(i,IdentityDto.class)).collect(Collectors.toList());
    }


}
