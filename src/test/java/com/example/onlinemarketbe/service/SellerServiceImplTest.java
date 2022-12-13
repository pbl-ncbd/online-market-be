package com.example.onlinemarketbe.service;

import com.example.onlinemarketbe.common.exception.NotFoundException;
import com.example.onlinemarketbe.dto.IdentityDto;
import com.example.onlinemarketbe.dto.SellerRegisterDto;
import com.example.onlinemarketbe.model.Identity;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.ReportRequest;
import com.example.onlinemarketbe.repositories.*;
import com.example.onlinemarketbe.services.ImgService;
import com.example.onlinemarketbe.services.impl.SellerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public
class SellerServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private IdentityImageRepository identityImageRepository;

    @Mock
    private ImgService imgService;

    @InjectMocks
    private SellerServiceImpl sellerService;

    @Test
    @DisplayName("Should return empty list when there is no seller")
    void getAllSellersShouldReturnEmptyListWhenThereIsNoSeller() {
        when(identityRepository.findByDeletedFalse()).thenReturn(null);
        assertEquals(0, sellerService.getAllSellers().size());
    }

    @Test
    @DisplayName("Should return all sellers")
    void getAllSellersShouldReturnAllSellers() {
        var identity = new Identity();
        identity.setId(1);
        identity.setName("name");
        identity.setGender("gender");
        identity.setAddress("address");
        identity.setConfirm(true);
        identity.setDeleted(false);

        var user = new User();
        user.setId(1);
        user.setActive(true);

        identity.setUser(user);

        when(identityRepository.findByDeletedFalse()).thenReturn(List.of(identity));

        var result = sellerService.getAllSellers();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should throw an exception when the seller is not found")
    void deleteSellerWhenSellerIsNotFoundThenThrowException() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> sellerService.deleteSeller(1));
    }

    @Test
    @DisplayName("Should delete the seller when the seller is found")
    void deleteSellerWhenSellerIsFound() {
        User user = new User();
        user.setId(1);
        user.setActive(true);
        Identity identity = new Identity();
        identity.setId(1);
        identity.setUser(user);
        user.setIdentity(identity);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(identityRepository.findByUser_Id(anyInt())).thenReturn(Optional.of(identity));
        sellerService.deleteSeller(1);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should create a report when the seller is found")
    void reportSellerWhenSellerIsFoundThenCreateReport() {
        User user = new User();
        user.setId(1);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        sellerService.reportSeller(1, new ReportRequest(1, anyInt(), "reason"));
        verify(reportRepository, times(1)).createReport(anyInt(), anyInt(), anyString());
    }

    @Test
    @DisplayName("Should throw an exception when the seller is not found")
    void reportSellerWhenSellerIsNotFoundThenThrowException() {
        var reportRequest = new ReportRequest(1, 2, "reason");
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sellerService.reportSeller(2, reportRequest));

        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("Should throw an exception when the seller is not found")
    void confirmSellerWhenSellerIsNotFoundThenThrowException() {
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> sellerService.confirmSeller(1));
    }

    @Test
    @DisplayName("Should add role seller to the user when the seller is found")
    void confirmSellerWhenSellerIsFoundThenAddRoleToUser() {
        int sellerId = 1;
        User user = new User();
        Identity identity = new Identity();
        identity.setUser(user);
        user.setIdentity(identity);
        when(userRepository.findById(sellerId)).thenReturn(Optional.of(user));

        sellerService.confirmSeller(sellerId);

        verify(userRepository, times(1)).findById(sellerId);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Should throw an exception when the user is not existed")
    void createSellerWhenUserIsNotExistedThenThrowException() {
        var sellerRegisterDto = new SellerRegisterDto();
        sellerRegisterDto.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sellerService.createSeller(sellerRegisterDto));
    }

    @Test
    @DisplayName("Should save the seller when the user is not existed")
    void createSellerWhenUserIsNotExisted() {
        var sellerRegisterDto = new SellerRegisterDto();
        sellerRegisterDto.setUserId(1);
        sellerRegisterDto.setName("name");
        sellerRegisterDto.setGender("gender");
        sellerRegisterDto.setAddress("address");
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> sellerService.createSeller(sellerRegisterDto));

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should update the seller when the user is existed")
    void createSellerWhenUserIsExisted() {
        var sellerRegisterDto = new SellerRegisterDto();
        sellerRegisterDto.setUserId(1);
        sellerRegisterDto.setName("name");
        sellerRegisterDto.setGender("gender");
        sellerRegisterDto.setAddress("address");
        var user = new User();
        user.setId(1);
        user.setActive(true);
        var identity = new Identity();
        identity.setId(1);
        identity.setName("name");
        identity.setGender("gender");
        identity.setAddress("address");
        identity.setUser(user);
        user.setIdentity(identity);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertThrows(NotFoundException.class, () -> sellerService.createSeller(sellerRegisterDto));

        verify(userRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should throw an exception when the seller does not exist")
    void getSellerIdentityWhenSellerNotExistsThenThrowException() {
        lenient().when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> sellerService.getSellerIdentity(1));
    }

    @Test
    @DisplayName("Should return the identity of the seller when the seller exists")
    void getSellerIdentityWhenSellerExists() {
        int userId = 1;
        User user = new User();
        user.setId(userId);
        Identity identity = new Identity();
        identity.setUser(user);
        lenient().when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        lenient().when(identityRepository.findById(userId)).thenReturn(Optional.of(identity));

        IdentityDto identityDto = sellerService.getSellerIdentity(userId);

        assertEquals(identityDto.getUser().getId(), userId);
    }
}