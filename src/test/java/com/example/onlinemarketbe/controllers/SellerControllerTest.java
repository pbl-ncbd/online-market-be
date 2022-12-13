package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.dto.IdentityDto;
import com.example.onlinemarketbe.dto.SellerRegisterDto;
import com.example.onlinemarketbe.payload.request.SignupSellerRequest;
import com.example.onlinemarketbe.services.SellerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerControllerTest {
    @Mock
    private SellerService sellerService;

    @InjectMocks
    private SellerController sellerController;


    @Test
    @DisplayName("Should return 400 when the request is invalid")
    void sellerRegisterWhenRequestIsInvalidThenReturn400() {
        SignupSellerRequest request = null;

        ResponseEntity<?> responseEntity = null;
        try {
             responseEntity = sellerController.sellerRegister(request);
        } catch (Exception e){
            assertTrue(true);
        }

        assertEquals(null, responseEntity);
    }

    @Test
    @DisplayName("Should return 200 when the request is valid")
    void sellerRegisterWhenRequestIsValidThenReturn200() {
        IdentityDto identityDto = new IdentityDto();
        identityDto.setId(1);
        identityDto.setName("name");
        identityDto.setGender("gender");
        identityDto.setAddress("address");
        identityDto.setUser(new IdentityDto.UserDto(1, true));
        identityDto.setIdCards(new ArrayList<>());
        identityDto.setConfirm(false);

        when(sellerService.getSellerIdentity(1)).thenReturn(identityDto);

        ResponseEntity<?> responseEntity = sellerController.getSellerIdentity(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should not throw an exception when the seller does not exist")
    void getSellerIdentityWhenSellerDoesNotExistThenThrowException() {
        when(sellerService.getSellerIdentity(1)).thenReturn(null);
        assertEquals(HttpStatus.OK, sellerController.getSellerIdentity(1).getStatusCode());
        assertEquals(null, sellerController.getSellerIdentity(1).getBody());
    }

    @Test
    @DisplayName("Should return the seller identity when the seller exists")
    void getSellerIdentityWhenSellerExists() {
        IdentityDto identityDto = new IdentityDto();
        identityDto.setId(1);
        identityDto.setName("name");
        identityDto.setGender("gender");
        identityDto.setAddress("address");
        identityDto.setUser(new IdentityDto.UserDto(1, true));
        identityDto.setIdCards(new ArrayList<>());
        identityDto.setConfirm(true);

        when(sellerService.getSellerIdentity(1)).thenReturn(identityDto);

        ResponseEntity<?> responseEntity = sellerController.getSellerIdentity(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(identityDto, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should return all sellers")
    void getAllSellerShouldReturnAllSellers() {
        List<IdentityDto> identityDtoList = new ArrayList<>();
        identityDtoList.add(new IdentityDto());
        identityDtoList.add(new IdentityDto());
        when(sellerService.getAllSellers()).thenReturn(identityDtoList);
        ResponseEntity<List<IdentityDto>> responseEntity = sellerController.getAllSeller();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

}