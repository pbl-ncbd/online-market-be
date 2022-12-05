package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.UrlImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlImgRepository extends JpaRepository<UrlImg,Integer> {
    public List<UrlImg> findAllByProductId(int id);
}
