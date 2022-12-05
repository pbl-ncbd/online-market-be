package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.EStatus;
import com.example.onlinemarketbe.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Integer> {
public Status findStatusById(int id);
public Status findStatusByName(EStatus eStatus);
}
