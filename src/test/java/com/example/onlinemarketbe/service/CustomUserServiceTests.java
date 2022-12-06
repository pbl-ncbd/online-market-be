package com.example.onlinemarketbe.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.example.onlinemarketbe.OnlineMarketBeApplication;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.RoleService;
import com.example.onlinemarketbe.services.impl.CustomUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;



//@SpringBootTest(classes = OnlineMarketBeApplication.class)
@RunWith(MockitoJUnitRunner.class)
@Transactional
public class CustomUserServiceTests {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;

    @Mock
    RoleService roleService;

    @Test
    public void registerNew() throws Exception {
        int result = customUserDetailsService.registerUser("admin1234","admin1234");
        assertEquals(1, result);
    }
    @Test
    public void registerOld() throws Exception {
        User user = new User();
//        when(userRepository.findUserByUsername("admin1234")).thenReturn(user);
        when(userRepository.findUserByUsername("admin1234")).thenReturn(user);
        int result = customUserDetailsService.registerUser("admin1234","admin1234");
        assertEquals(2, result);
    }

    @Test
    public void findUserByUsernameTest() throws Exception {
        User user = new User();
        when(userRepository.findUserByUsername("admin1234")).thenReturn(user);
        var result = customUserDetailsService.findUserByUsername("admin1234");
        assertEquals(user, result);
    }

    @Test
    public void findUserByUsernameDontExistTest() throws Exception {
        when(userRepository.findUserByUsername("admin1234")).thenReturn(null);
        try {
            customUserDetailsService.findUserByUsername("admin1234");
        } catch (Exception e) {
            assertEquals("User not found", e.getMessage());
        }
    }

    @Test
    @Transactional
    public void loadUserIdByUsernameTest() throws Exception {
        User user = new User();
        user.setUsername("admin1234");
        user.setId(1);
        userRepository.saveAndFlush(user);
        when(userRepository.findUserByUsername("admin1234")).thenReturn(user);
        var result = customUserDetailsService.loadUserIdByUsername(user.getUsername());
        assertEquals(1, result);
    }

    @Test
    public void loadUserIdByUsernameDontExistTest() throws Exception {
        when(userRepository.findUserByUsername("admin1234")).thenReturn(null);
        try {
            customUserDetailsService.findUserByUsername("admin1234");
        } catch (Exception e) {
            assertEquals("User not found", e.getMessage());
        }
    }
}