package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Information;
import com.example.onlinemarketbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {

    public Information findInformationById(int id);


}
