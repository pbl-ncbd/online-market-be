package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.ERole;
import com.example.onlinemarketbe.model.Role;
import com.example.onlinemarketbe.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleResult;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role findRoleByName(ERole name){
        return roleRepository.findRoleByName(name);
    }

}
