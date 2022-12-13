package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.dto.IdentityDto;
import com.example.onlinemarketbe.dto.SellerIdentityDto;
import com.example.onlinemarketbe.dto.SellerRegisterDto;
import com.example.onlinemarketbe.payload.request.SignupSellerRequest;
import com.example.onlinemarketbe.services.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin()
@RestController
@RequestMapping(value = "/api/seller")
//@Api( tags = "manage-seller")
public class SellerController {
    private final SellerService sellerService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SellerController(final SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IdentityDto>> getAllSeller(){
        List<IdentityDto> identityDtoList = sellerService.getAllSellers();
        return new ResponseEntity<>(identityDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSellerIdentity(@PathVariable("id") int sellerId){
        return new ResponseEntity<>(sellerService.getSellerIdentity(sellerId), HttpStatus.OK);
    }

    @PostMapping(value = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> sellerRegister(@ModelAttribute @Valid SignupSellerRequest signupSellerRequest){
        SellerRegisterDto sellerRegisterDto = modelMapper.map(signupSellerRequest, SellerRegisterDto.class);
        log.info(sellerRegisterDto.toString());
        sellerService.createSeller(sellerRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/updateIdentity", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateSellerIdentity(@PathVariable("id") int sellerId,@ModelAttribute @Valid SignupSellerRequest signupSellerRequest) {
        SellerRegisterDto sellerRegisterDto = modelMapper.map(signupSellerRequest, SellerRegisterDto.class);
        sellerService.createSeller(sellerRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> deleteSeller(@PathVariable("id") int sellerId) {
        sellerService.deleteSeller(sellerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
