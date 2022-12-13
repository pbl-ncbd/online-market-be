package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByUsername(String username);

    public User findUserById(int id);


}

