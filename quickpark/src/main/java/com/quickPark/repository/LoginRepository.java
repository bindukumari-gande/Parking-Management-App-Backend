package com.quickPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickPark.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

	public String findByEmail(String email);
}
