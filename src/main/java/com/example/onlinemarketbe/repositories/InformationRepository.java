package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {
}
