package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.common.enums.ERole;
import com.example.onlinemarketbe.model.Role;



public interface RoleService {
    public Role findRoleByName(ERole name);



}
