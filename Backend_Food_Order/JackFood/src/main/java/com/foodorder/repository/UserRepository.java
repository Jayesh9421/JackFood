package com.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorder.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String username);
}
