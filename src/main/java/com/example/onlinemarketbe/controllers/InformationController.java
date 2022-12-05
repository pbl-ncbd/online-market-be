package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.services.impl.InformationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api")
@Tag( name = "profile")
public class InformationController {

    private final InformationServiceImpl informationService;

    public InformationController(InformationServiceImpl informationService) {
        this.informationService = informationService;
    }

    @PostMapping("/update-profile")
    @Operation(summary = "04/12/2022 by Linh : This is update profile of user by id")
    public ResponseEntity<?> updateProfile(Principal principal , @RequestBody InformationRequest request){
        try{
            return ResponseEntity.ok(informationService.updateProfile(principal.getName(), request));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }
}
