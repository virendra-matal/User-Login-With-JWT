package com.practice.jwt.Repository;

import com.practice.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer> {
    public User findByUsername(String username);
}
