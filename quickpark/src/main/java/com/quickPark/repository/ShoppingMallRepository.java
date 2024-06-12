package com.quickPark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quickPark.entity.ShoppingMall;
@Repository
public interface ShoppingMallRepository  extends JpaRepository <ShoppingMall,Integer>{
@Query(value="delete from ShoppingMall m where m.mallId= :mallId")
@Modifying
	int deleteShoppingMall( @Param ("mallId")int mallId);

@Query("select m from ShoppingMall m")
@Modifying
List <ShoppingMall> getAllShoppingMalls();


}
