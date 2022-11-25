package com.example.onlinemarketbe.controllers;



import com.example.onlinemarketbe.common.UserDetailsImpl;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.LoginRequest;
import com.example.onlinemarketbe.payload.request.SignupRequest;
import com.example.onlinemarketbe.payload.response.MessageResponse;
import com.example.onlinemarketbe.payload.response.UserInfoResponse;
import com.example.onlinemarketbe.security.jwt.JwtUtils;
import com.example.onlinemarketbe.services.CustomUserDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings("checkstyle:Indentation")
@CrossOrigin ()
@RestController
@RequestMapping ("/api/auth")
@Api (tags = "api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    JwtUtils jwtUtils;
    

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @serialData
     * @deprecated Some javadoc.
     */

    @ApiOperation (value = "20/11/2022 by Vuong : signup new account teacher")
    @PostMapping ("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest) {
        try {
            if (customUserDetailsService.registerUser(signupRequest.getUsername(),
                    signupRequest.getPassword()) == 1) {
                LoginRequest loginRequest =
                        new LoginRequest(signupRequest.getUsername(),
                                signupRequest.getPassword());

                return authenticateUser(loginRequest);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            return ResponseEntity.ok().body(new MessageResponse("Register failed"));
        }
    }



    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @serialData
     * @deprecated Some javadoc.
     */
    @ApiOperation (value = "20/11/2022 by Vuong : login ")
    @PostMapping ("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                                            loginRequest.getUsername(),
                                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails =
                    (UserDetailsImpl) authentication.getPrincipal();

            ResponseCookie jwtCookie =
                    jwtUtils.generateJwtCookie(userDetails);

            User user = customUserDetailsService
                    .findUserByUsername(loginRequest.getUsername());

            return ResponseEntity.ok()
                    .body(new UserInfoResponse(user, jwtCookie.getValue()));
        } catch (Exception ex) {

            return ResponseEntity.ok().body(new MessageResponse("Login failed"));
        }

    }

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @serialData
     * @deprecated Some javadoc.
     */
    @ApiOperation (value = "20/11/2022 by Vuong : get current user ")
    @GetMapping("/current")
    public ResponseEntity<?> currentUserName(Principal principal) {
        return ResponseEntity.ok()
                .body(Objects
                        .requireNonNullElseGet(principal,
                                () -> new MessageResponse("Don't exist user now")));
    }

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @serialData
     * @deprecated Some javadoc.
     */
    @PreAuthorize ("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER')")
    @ApiOperation (value = "20/11/2022 by Vuong : logout ")
    @PostMapping ("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}
