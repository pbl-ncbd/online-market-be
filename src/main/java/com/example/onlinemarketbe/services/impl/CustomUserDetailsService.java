package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.common.UserDetailsImpl;
import com.example.onlinemarketbe.common.enums.ERole;
import com.example.onlinemarketbe.model.Role;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepo;

    private final RoleService roleService;

    CustomUserDetailsService(UserRepository userRepo,
                             RoleService roleService){
        this.roleService = roleService;
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImpl.build(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles());
    }


    public int loadUserIdByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.getId();
    }
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public int registerUser(String username, String password) {

        try {
            User existedUser = userRepo.findUserByUsername(username);
            if (existedUser == null) {
                User user = new User();
                Set<Role> roles = new HashSet<>();
                Role userRole = roleService.findRoleByName(ERole.ROLE_BUYER);
                roles.add(userRole);
                user.setUsername(username);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
                user.setRoles(roles);
                userRepo.save(user);
                logger.info("Created success new user: " + user.getId());
                return 1;
            } else {
                logger.info("Username is existed: " + existedUser.getUsername());
                return 2;
            }
        } catch (Exception e) {
            logger.info("Save user exception" + e);
            return 0;
        }
    }

}