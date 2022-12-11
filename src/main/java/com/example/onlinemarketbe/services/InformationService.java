package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.payload.request.InformationRequest;
import org.springframework.http.ResponseEntity;

public interface InformationService {
    public ResponseEntity<?> updateProfile(String username, InformationRequest informationRequest);
    public ResponseEntity<?> getProfile(String username);
    public ResponseEntity<?> getAddress(String username);


}
