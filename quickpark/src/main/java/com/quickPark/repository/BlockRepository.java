package com.quickPark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quickPark.entity.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Integer> {

}
