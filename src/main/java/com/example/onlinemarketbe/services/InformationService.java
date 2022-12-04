package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.District;
import com.example.onlinemarketbe.model.Information;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.repositories.DistrictRepository;
import com.example.onlinemarketbe.repositories.InformationRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InformationService {
    @Autowired
    InformationRepository informationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DistrictRepository districtRepository;

    public ResponseEntity<?> updateProfile(String username, InformationRequest informationRequest)
    {
        User user = userRepository.findUserByUsername(username);
        if(user==null)
        {
            return ResponseEntity.ok("Not logged in yet") ;
        }
        else
        {
            Information infor = user.getInformation();
            if (infor == null)
            {
                infor = new Information();
            }
            infor.setName(informationRequest.getName());
            infor.setPhone(informationRequest.getPhone());
            infor.setBirthDate(informationRequest.getBirthDate());
            District district = districtRepository.getDistrictById(informationRequest.getDistrict_id());
            infor.setDistrict(district);
            infor.setAddress(informationRequest.getAddress());
            informationRepository.save(infor);
            user.setInformation(infor);
            return ResponseEntity.ok(infor);
        }
    }


}
