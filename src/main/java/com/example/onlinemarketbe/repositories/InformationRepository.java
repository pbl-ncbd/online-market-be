package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {

    public Information findInformationById(int id);


}
