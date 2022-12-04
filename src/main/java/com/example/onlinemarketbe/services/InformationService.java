package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.repositories.InformationRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InformationService {
    @Autowired
    InformationRepository informationRepository;

    @Autowired
    UserRepository userRepository;


}
