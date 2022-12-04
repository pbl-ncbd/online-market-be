package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.District;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    public List<District> getDistrictsByProvinceId(int id);

    public District getDistrictById(int id);
}
