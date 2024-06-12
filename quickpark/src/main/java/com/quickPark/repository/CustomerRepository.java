package com.quickPark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quickPark.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query(value = "delete from Customer c where c.customerId= :customerId")
	@Modifying
	int deleteCustomers(@Param("customerId") int customerId);

	@Query("select c from Customer c")
	@Modifying
	List<Customer> getAllCustomers();
	
	@Query(value = "update Customer c set c.customerName= :customerName where c.customerId = :customerId")
	@Modifying
	public void updateCustomerName(@Param("customerName") String customerName,@Param("customerId") int customerId);
	
	@Query(value = "update Customer c set c.customerEmail= :customerEmail where c.customerId = :customerId")
	@Modifying
	public void updateCustomerEmail(@Param("customerEmail") String customerEmail, @Param("customerId") int customerId);
	
	@Query(value = "update Customer c set c.password= :password where c.customerId = :customerId")
	@Modifying
	public void updateCustomerPassword(@Param("password") String password, @Param("customerId") int customerId);
}
