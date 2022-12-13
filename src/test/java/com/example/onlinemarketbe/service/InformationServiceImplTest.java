package com.example.onlinemarketbe.service;

import com.example.onlinemarketbe.model.District;
import com.example.onlinemarketbe.model.Information;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.repositories.DistrictRepository;
import com.example.onlinemarketbe.repositories.InformationRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.impl.InformationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InformationServiceImplTest {

    @Mock
    private InformationRepository informationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DistrictRepository districtRepository;

    @InjectMocks
    private InformationServiceImpl informationService;

    @Test
    @DisplayName("Should return all districts")
    void getAllDistrictShouldReturnAllDistricts() {
        District district = new District();
        district.setId(1);
        district.setDistrict_name("Quan 1");
        when(districtRepository.findAll()).thenReturn(List.of(district));

        List<District> response = informationService.getAllDistrict();

        assertEquals(1, response.size());
    }

    @Test
    @DisplayName("Should return not found user when user is not logged in")
    void getAddressWhenUserIsNotLoggedInThenReturnNotFoundUser() {
        String username = "username";
        when(userRepository.findUserByUsername(anyString())).thenReturn(null);

        ResponseEntity<?> response = informationService.getAddress(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Not found user", response.getBody());
    }

    @Test
    @DisplayName("Should return address when user is logged in")
    void getAddressWhenUserIsLoggedInThenReturnAddress() {
        User user = new User();
        Information information = new Information();
        District district = new District();
        information.setAddress("address");
        information.setDistrict(district);
        user.setInformation(information);

        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        ResponseEntity<?> response = informationService.getAddress("username");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should return not logged in yet when the user is not logged in")
    void getProfileWhenUserIsNotLoggedInThenReturnNotLoggedInYet() {
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setActive(false);
        when(userRepository.findUserByUsername(anyString())).thenReturn(null);
        ResponseEntity<?> response = informationService.getProfile(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Not logged in yet", response.getBody());
    }

    @Test
    @DisplayName("Should return the information of the user when the user is logged in")
    void getProfileWhenUserIsLoggedInThenReturnTheInformationOfTheUser() {
        String username = "username";
        User user = new User();
        Information information = new Information();
        information.setName("name");
        information.setPhone("phone");
        information.setBirthDate(new Date(2020, 10, 10));
        District district = new District();
        district.setDistrict_name("district");
        information.setDistrict(district);
        information.setAddress("address");
        user.setInformation(information);

        when(userRepository.findUserByUsername(anyString())).thenReturn(user);

        ResponseEntity<?> responseEntity = informationService.getProfile(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(information, responseEntity.getBody());
    }

    @Test
    @DisplayName("Should return not logged in yet when the user is not logged in")
    void updateProfileWhenUserIsNotLoggedInThenReturnNotLoggedInYet() {
        String username = "username";
        InformationRequest informationRequest = new InformationRequest();
        informationRequest.setName("name");
        informationRequest.setPhone("phone");
        informationRequest.setBirthDate(new Date(System.currentTimeMillis()));
        informationRequest.setAddress("address");
        informationRequest.setDistrict_id(1);

        when(userRepository.findUserByUsername(anyString())).thenReturn(null);

        ResponseEntity<?> responseEntity =
                informationService.updateProfile(username, informationRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Not logged in yet", responseEntity.getBody());
    }

    @Test
    @DisplayName("Should return the information when the user is logged in")
    void updateProfileWhenUserIsLoggedInThenReturnTheInformation() {
        String username = "username";
        InformationRequest informationRequest = new InformationRequest();
        informationRequest.setName("name");
        informationRequest.setPhone("phone");
        informationRequest.setBirthDate(new Date(System.currentTimeMillis()));
        informationRequest.setAddress("address");
        informationRequest.setDistrict_id(1);

        User user = new User();
        Information information = new Information();

        District district = new District();

        when(userRepository.findUserByUsername(anyString())).thenReturn(user);
        when(districtRepository.getDistrictById(anyInt())).thenReturn(district);

        ResponseEntity<?> responseEntity =
                informationService.updateProfile(username, informationRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}