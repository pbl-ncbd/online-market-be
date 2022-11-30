package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.ERole;
import com.example.onlinemarketbe.model.Role;
import com.example.onlinemarketbe.repositories.RoleRepository;
import com.example.onlinemarketbe.services.RoleService;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public Role findRoleByName(ERole name){
        return roleRepository.findRoleByName(name);
    }

}
