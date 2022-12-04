package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.services.InformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api")
@Tag( name = "profile")
public class InformationController {

    @Autowired
    InformationService informationService;

    @PostMapping("/update-profile")
    @Operation(summary = "04/12/2022 by Linh : This is update profile of user by id")
    public ResponseEntity<?> updateProfile(Principal principal , @RequestBody InformationRequest request){
        return ResponseEntity.ok(informationService.updateProfile(principal.getName(), request));
    }
}
