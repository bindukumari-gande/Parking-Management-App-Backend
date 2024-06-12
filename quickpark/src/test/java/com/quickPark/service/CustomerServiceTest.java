package com.quickPark.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.quickPark.entity.Customer;
import com.quickPark.entity.Login;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.exceptions.CustomerNotPresentException;
import com.quickPark.repository.CustomerRepository;
import com.quickPark.repository.LoginRepository;
import com.quickPark.repository.MyBookingRepository;

/**
 * @author aypatel
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

	private CustomerServiceImpl customerServiceImpl;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private LoginRepository loginRepository;

	@Mock
	private MyBookingRepository myBookingRepository;

	@BeforeEach
	void setup() {
		this.customerServiceImpl = new CustomerServiceImpl(this.customerRepository, this.loginRepository,
				this.myBookingRepository);
	}
	
	private Customer customer;

	@Test
	void addCustomerTest() {

		Login login = new Login();
		Customer customer = new Customer(31, "Ravikishor", "ravikishor1@gmail.com", 9755468705L, "ravi#123", login);
		loginRepository.save(login);
		customerRepository.save(customer);

		assertThat(customerServiceImpl.addCustomer(customer));
	}

	/*
	 * @Mock private LoginRepository loginRepository;
	 */

	// private Customer customer;

	/*
	 * @BeforeEach void customerIntializerSetup() throws Exception {
	 * this.customerServiceImpl = new CustomerServiceImpl(this.customerRepository);
	 * 
	 * 
	 * Login login =
	 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
	 * "customer").build(); Customer customer =
	 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
	 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").login
	 * (login).build();
	 * 
	 * Mockito.when(customerRepository.save(customer)).thenReturn(customer);
	 * Mockito.when(customerRepository.findAll()).thenReturn(Collections.
	 * singletonList(customer));
	 * 
	 * }
	 */

	/*
	 * @Test public void addCustomerTestCase() throws Exception {
	 * 
	 * Customer customer =
	 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
	 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").build
	 * ();
	 * 
	 * Customer customer2 = customerServiceImpl.addCustomer(customer);
	 * 
	 * assertEquals(customer, customer2);
	 * 
	 * // Customer fetchedCustomer = customerRepository.findById(24).get(); //
	 * verify(customerRepository).findById(24); // assertEquals(fetchedCustomer,
	 * customer); }
	 */

	/*
	 * @Test public void getAllCustomers() throws CustomerNotPresentException {
	 * 
	 * Customer customer =
	 * Customer.builder().customerEmail("raghu123@gmail.com").customerMobileNo(
	 * 76104577) .customerName("Raghu").password("Raghu@123").build();
	 * 
	 * List<Customer> fetchCustomers = customerService.getAllCustomers();
	 * 
	 * assertEquals(fetchCustomers, Collections.singletonList(customer)); }
	 */

	@Test
	public void getAllCustomers() throws CustomerNotPresentException {
		/*
		 * Login login =
		 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
		 * "customer").build();
		 * 
		 * Customer customer =
		 * Customer.builder().customerId(27).customerEmail("ravi@gmail.com")
		 * .customerMobileNo(9767666877l).customerName("Ravi").password("ravi@123").
		 * login(login) .build();
		 */
		// Mockito.when(customerRepository.save(customer)).thenReturn(customer);

		/*
		 * Login login =
		 * Login.builder().email("raghu123@gmail.com").password("Raghu@123").role(
		 * "customer").build(); Customer customer =
		 * Customer.builder().customerId(24).customerEmail("raghu123@gmail.com")
		 * .customerMobileNo(76104577).customerName("Raghu").password("Raghu@123").login
		 * (login).build();
		 * 
		 * List<Customer> customerList = customerServiceImpl.getAllCustomers();
		 * assertEquals(customerList, Collections.singletonList(customer));
		 */

		List<Customer> customers = customerRepository.findAll();
		Assertions.assertNotNull(customers);

	}

	@Test
	public void deleteCustomer() throws CustomerNotPresentException {

		Login login = new Login();
		Customer customer = new Customer(31, "Ravikishor", "ravikishor1@gmail.com", 9755468705L, "ravi#123", login);
		loginRepository.save(login);
		customerRepository.save(customer);

		customerServiceImpl.deleteCustomer(customer.getCustomerId());
		List<Customer> customers = customerRepository.findAll();

		Assertions.assertFalse(customers.contains(customer));
	}

	@Test
	public void viewAllBookingsByCustomerIdTest() {
		ShoppingMall mall = new ShoppingMall();

		Login login = new Login();
		Customer customer = new Customer(31, "Ravikishor", "ravikishor1@gmail.com", 9755468705L, "ravi#123", login);
		loginRepository.save(login);
		customerRepository.save(customer);

		MyBooking booking1 = new MyBooking(21, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "Booked",
				"UPI", 10, "MP05AF1234", 2, mall, customer);
		MyBooking booking2 = new MyBooking(21, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(1), "Booked",
				"UPI", 10, "MP05AF1234", 2, mall, customer);


		List<MyBooking> myBookings = new ArrayList<>();
		myBookings.add(booking1);
		myBookings.add(booking2);

		//List<MyBooking> result = customerServiceImpl.viewBookingsByCustomerId(customer.getCustomerId());

		//Assertions.assertEquals(2, result.size());
		//Assertions.assertTrue(result.contains(booking1));
		//Assertions.assertTrue(result.contains(booking2));

	}

}
