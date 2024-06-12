package com.quickPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickPark.entity.Slot;

public interface SlotRepository extends JpaRepository<Slot, Integer> {

}
