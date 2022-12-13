package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    public List<District> getDistrictsByProvinceId(int id);

    public District getDistrictById(int id);
}
