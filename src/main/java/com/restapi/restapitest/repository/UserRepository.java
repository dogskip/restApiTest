package com.restapi.restapitest.repository;

import com.restapi.restapitest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}