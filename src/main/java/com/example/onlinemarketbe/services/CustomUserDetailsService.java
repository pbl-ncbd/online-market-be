package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.common.UserDetailsImpl;
import com.example.onlinemarketbe.model.ERole;
import com.example.onlinemarketbe.model.Role;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.repositories.RoleRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** Some javadoc. // OK
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings({"checkstyle:Indentation"})
@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImpl.build(user.getId(), user.getUsername(), user.getPassword(), user.getRoles());
    }

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public int loadUserIdByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.getId();
    }


    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public int registerUser(String username, String password) {

        try {
            User existedUser = userRepo.findUserByUsername(username);
            System.out.println(userRepo.findUserByUsername(username));
            if (existedUser == null) {
                Set<Role> roles = new HashSet<>();
                Role userRole = roleService.findRoleByName(ERole.ROLE_BUYER);
                roles.add(userRole);
                User user = new User();
                user.setUsername(username);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
                user.setRoles(roles);
                userRepo.save(user);

                logger.info("Created " + user.getRoles().toArray() + " " + user.getId());
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