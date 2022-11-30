package com.example.onlinemarketbe.controllers;



import com.example.onlinemarketbe.common.UserDetailsImpl;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.LoginRequest;
import com.example.onlinemarketbe.payload.request.SignupRequest;
import com.example.onlinemarketbe.payload.response.MessageResponse;
import com.example.onlinemarketbe.payload.response.UserInfoResponse;
import com.example.onlinemarketbe.security.jwt.JwtUtils;
import com.example.onlinemarketbe.services.impl.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.Objects;


@CrossOrigin ()
@RestController
@RequestMapping ("/api/auth")
@Tag(name = "api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtils jwtUtils;

    AuthController(AuthenticationManager authenticationManager,
                   CustomUserDetailsService customUserDetailsService,
                   JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
    }


    @Operation(summary = "20/11/2022 by Vuong : signup new account teacher")
    @PostMapping ("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest) {

            if (customUserDetailsService.registerUser(signupRequest.getUsername(),
                    signupRequest.getPassword()) == 1) {
                LoginRequest loginRequest =
                        new LoginRequest(signupRequest.getUsername(),
                                signupRequest.getPassword());

                return authenticateUser(loginRequest);
            }

            return ResponseEntity.ok().body(new MessageResponse("register failed"));
        }



    @Operation(summary = "20/11/2022 by Vuong : login ")
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


    @Operation(summary = "20/11/2022 by Vuong : get current user ")
    @GetMapping("/current")
    public ResponseEntity<?> currentUserName(Principal principal) {
        return ResponseEntity.ok()
                .body(Objects
                        .requireNonNullElseGet(principal,
                                () -> new MessageResponse("Don't exist user now")));
    }


    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    @Operation(summary = "20/11/2022 by Vuong : logout ")
    @PostMapping ("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

}
