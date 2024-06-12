package com.quickPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickPark.entity.MyBooking;

public interface MyBookingRepository extends JpaRepository<MyBooking, Integer> {

}
