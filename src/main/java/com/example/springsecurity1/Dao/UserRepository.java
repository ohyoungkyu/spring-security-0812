package com.example.springsecurity1.Dao;

import com.example.springsecurity1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
