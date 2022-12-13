package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.District;
import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.payload.response.AddressResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InformationService {
    public ResponseEntity<?> updateProfile(String username, InformationRequest informationRequest);
    public ResponseEntity<?> getProfile(String username);
    public ResponseEntity<?> getAddress(String username);
    public List<District> getAllDistrict();


}
