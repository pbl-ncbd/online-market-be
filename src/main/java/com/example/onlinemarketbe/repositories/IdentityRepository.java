package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IdentityRepository extends JpaRepository<Identity, Integer> {
    List<Identity> findByDeletedFalse();

    Optional<Identity> findByUser_Id(int id);

}
