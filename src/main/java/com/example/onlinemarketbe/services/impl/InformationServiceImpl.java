package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.District;
import com.example.onlinemarketbe.model.Information;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.InformationRequest;
import com.example.onlinemarketbe.payload.response.AddressResponse;
import com.example.onlinemarketbe.repositories.DistrictRepository;
import com.example.onlinemarketbe.repositories.InformationRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.InformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationServiceImpl implements InformationService {

    private final InformationRepository informationRepository;

    private final UserRepository userRepository;

    private final DistrictRepository districtRepository;

    public InformationServiceImpl(InformationRepository informationRepository, UserRepository userRepository, DistrictRepository districtRepository) {
        this.informationRepository = informationRepository;
        this.userRepository = userRepository;
        this.districtRepository = districtRepository;
    }

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
            userRepository.save(user);
            return ResponseEntity.ok(infor);
        }
    }

    public ResponseEntity<?> getProfile(String username){
        User user = userRepository.findUserByUsername(username);
        if(user==null)
        {
            return ResponseEntity.ok("Not logged in yet") ;
        }
        else
        {
            Information infor = user.getInformation();
            if(infor==null)
            {
                Information information= new Information();
                informationRepository.save(information);
                user.setInformation(information);
                userRepository.save(user);
            }
            return ResponseEntity.ok(infor);
        }
    }

    public ResponseEntity<?> getAddress(String username){
        User user = userRepository.findUserByUsername(username);
        if(user==null)
        {
            return ResponseEntity.ok("Not found user") ;
        }
        else
        {
            Information information= user.getInformation();
            if(information==null)
            {
                Information information1= new Information();
                informationRepository.save(information1);
                user.setInformation(information1);
                userRepository.save(user);
            }
            District district = user.getInformation().getDistrict();
            String address = user.getInformation().getAddress();
            AddressResponse response = new AddressResponse(address, district);
            return ResponseEntity.ok(response);
        }
    }
public List<District> getAllDistrict()
{
    return districtRepository.findAll();
}


}

