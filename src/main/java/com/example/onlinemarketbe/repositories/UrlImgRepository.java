package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.UrlImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlImgRepository extends JpaRepository<UrlImg,Integer> {
    public List<UrlImg> findAllByProductId(int id);
}
