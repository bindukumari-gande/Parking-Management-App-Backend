package com.quickPark.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quickPark.entity.AuthoriseUser;
import com.quickPark.entity.Block;
import com.quickPark.entity.Customer;
import com.quickPark.entity.Login;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.Payment;
import com.quickPark.entity.ShoppingMall;
import com.quickPark.entity.Slot;
import com.quickPark.exceptions.BookingNotFoundException;
import com.quickPark.exceptions.CustomerNotPresentException;
import com.quickPark.exceptions.EmptyFieldException;
import com.quickPark.exceptions.NoSuchBlockExistsException;
import com.quickPark.exceptions.SlotNotAvailableException;
import com.quickPark.repository.BlockRepository;
import com.quickPark.repository.CustomerRepository;
import com.quickPark.repository.LoginRepository;
import com.quickPark.repository.MyBookingRepository;
import com.quickPark.repository.ShoppingMallRepository;
import com.quickPark.repository.SlotRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	@PersistenceContext
	private EntityManager em;

	public CustomerServiceImpl(CustomerRepository customerRepository, LoginRepository loginRepository,
			MyBookingRepository myBookingRepository) {
		this.customerRepository = customerRepository;
		this.loginRepository = loginRepository;
		this.myBookingRepository = myBookingRepository;
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private MyBookingRepository myBookingRepository;

	@Autowired
	private ShoppingMallRepository mallRepository;

	@Autowired
	private BlockRepository blockRepository;

	@Autowired
	private SlotRepository slotRepository;

	public Customer addCustomer(Customer customer) {

		if (customer.getPassword() == "" || customer.getCustomerEmail() == "") {
			throw new EmptyFieldException("Enter the valid data");
		} else {

			Login login = new Login();
			login.setEmail(customer.getCustomerEmail());
			login.setPassword(customer.getPassword());
			login.setRole("customer");
			customer.setLogin(login);

			loginRepository.save(login);

			Customer returnCustomer = customerRepository.save(customer);

			return returnCustomer;
		}

	}

	@Override
	public void deleteCustomer(int customerId) {

		customerRepository.deleteById(customerId);

	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer updatedCustomer, int customerId) {
		Optional<Customer> fetchedCustomer = customerRepository.findById(customerId);

		Login login = loginRepository.findById(fetchedCustomer.get().getLogin().getLoginId()).get();

		if (fetchedCustomer.isEmpty() || fetchedCustomer == null) {
			throw new CustomerNotPresentException("Not Present");
		} else {
//			fetchedCustomer.get().setCustomerId(customerId);
//			fetchedCustomer.get().setCustomerName(updatedCustomer.getCustomerName());
//			fetchedCustomer.get().setCustomerEmail(updatedCustomer.getCustomerEmail());
//			fetchedCustomer.get().setCustomerMobileNo(updatedCustomer.getCustomerMobileNo());
//			fetchedCustomer.get().setPassword(updatedCustomer.getPassword());

			fetchedCustomer.get().setCustomerEmail(updatedCustomer.getCustomerEmail());
			login.setEmail(updatedCustomer.getCustomerEmail());

			if (updatedCustomer.getCustomerEmail() == "") {
				fetchedCustomer.get().setCustomerEmail(fetchedCustomer.get().getCustomerEmail());
				// customerRepository.updateCustomerEmail(fetchedCustomer.get().getCustomerEmail(),
				// customerId);
			} else {
				// fetchedCustomer.get().setCustomerEmail(updatedCustomer.getCustomerEmail());
				customerRepository.updateCustomerEmail(updatedCustomer.getCustomerEmail(), customerId);
			}

			if (updatedCustomer.getPassword() == "") {
				fetchedCustomer.get().setPassword(fetchedCustomer.get().getPassword());
				login.setPassword(fetchedCustomer.get().getPassword());
				// customerRepository.updateCustomerPassword(fetchedCustomer.get().getPassword(),
				// customerId);

			} else {
				// fetchedCustomer.get().setPassword(updatedCustomer.getPassword());
				// login.setPassword(updatedCustomer.getPassword());

				customerRepository.updateCustomerPassword(updatedCustomer.getPassword(), customerId);
			}
			if (updatedCustomer.getCustomerMobileNo() == 0) {
				fetchedCustomer.get().setCustomerMobileNo(fetchedCustomer.get().getCustomerMobileNo());
			} else {
				fetchedCustomer.get().setCustomerMobileNo(updatedCustomer.getCustomerMobileNo());
			}
			if (updatedCustomer.getCustomerName() == "") {
				fetchedCustomer.get().setCustomerName(fetchedCustomer.get().getCustomerName());
				// customerRepository.updateCustomerName(fetchedCustomer.get().getCustomerName(),
				// customerId);
			} else {
				// fetchedCustomer.get().setCustomerName(updatedCustomer.getCustomerName());
				customerRepository.updateCustomerName(updatedCustomer.getCustomerName(), customerId);
			}

			// login.setRole(loginRepository
			// .findById(customerRepository.findById(customerId).get().getLogin().getLoginId()).get().getRole());

			loginRepository.save(login);

			fetchedCustomer.get().setLogin(login);
			// fetchedCustomer.get().setCustomerId(fetchedCustomer.get().getCustomerId());
		}

		return customerRepository.save(fetchedCustomer.get());

	}

	@Override
	public List<Customer> getAllCustomers() {

		if (customerRepository.findAll().isEmpty()) {
			throw new CustomerNotPresentException("Customers not found");
		}

		return customerRepository.findAll();

	}


	// this method in progress
	@Override
	public MyBooking addBooking(int customerId, int mallId, int blockId, int slotId, int vehicleType,
			String vehicleNo) {

		MyBooking myBooking = new MyBooking();

		Optional<Customer> customer = customerRepository.findById(customerId);
		Optional<ShoppingMall> mall = mallRepository.findById(mallId);

		if (customer.isEmpty() || mall.isEmpty()) {
			throw new CustomerNotPresentException("Not found");
		} else {

			Block selectedBlock;
			if (blockRepository.findById(blockId).isEmpty()) {
				throw new NoSuchBlockExistsException("Block not present");
			} else {
				selectedBlock = blockRepository.findById(blockId).get();
			}

			List<Slot> slotList = slotRepository.findAll();

			for (Slot slot : slotList) {

				if (slot.getStatus().equals("Empty") && slot.getBlock().getBlockId() == selectedBlock.getBlockId()) {

					myBooking.setCustomer(customer.get());
					myBooking.setMall(mall.get());
					myBooking.setSlotDate(LocalDate.now());
					myBooking.setStartTime(LocalTime.now());
					myBooking.setVehicleNo(vehicleNo);
					myBooking.setVehicleType(vehicleType);
					myBooking.setEndTime(LocalTime.now().plusHours(1));
					myBooking.setPayment("50");
					myBooking.setStatus("Booked");
					myBooking.setTotalprice(
							50 * (java.time.Duration.between(LocalTime.now(), LocalTime.now().plusHours(1)).toHours()));
					Slot selectedSlot;
					selectedSlot = slot;
					selectedSlot.setStatus("Booked");
					slotRepository.save(selectedSlot);
					break;
					// customerRepository.save(customer.get());

				}
			}

		}
		return myBookingRepository.save(myBooking);

	}

//created payment class

	@Override
	public Payment checkout(int customerId) {

		Payment payment = new Payment();

		if (customerRepository.findById(customerId).isEmpty()) {
			throw new CustomerNotPresentException("Customer does not exist!");
		} else {
			Customer customer = customerRepository.findById(customerId).get();

			List<MyBooking> bookings = myBookingRepository.findAll();

			if (bookings.isEmpty())

				throw new BookingNotFoundException("No bookings ");

			else {

				for (MyBooking myBooking : bookings) {

					if (myBooking.getCustomer().equals(customer)) {

						long diffDuration = java.time.Duration.between(myBooking.getStartTime(), LocalTime.now())
								.toHours();
						payment.setTotalPrice(diffDuration * payment.getFarePerHour());
						payment.setBookingId(myBooking.getBookingId());
						payment.setDate(LocalDate.now());
						payment.setStartTime(myBooking.getStartTime());
						payment.setEndTime(LocalTime.now());
						payment.setMessage("Success");

						// setting myBooking
						myBooking.setEndTime(LocalTime.now());
						myBooking.setTotalprice(diffDuration * payment.getFarePerHour());
						myBooking.setStatus("Completed");

						myBookingRepository.save(myBooking);
						break;

					}

				}
				if (payment.getBookingId() <= 0) {
					throw new BookingNotFoundException("No booking found for " + customer.getCustomerName());
				}

			}
		}
		return payment;

	}

	@Override
	public List<MyBooking> viewAllMyBookings() {

		if (myBookingRepository.findAll().isEmpty()) {
			throw new BookingNotFoundException("No Booking Present");
		}
		List<MyBooking> book = myBookingRepository.findAll();
		return book;

	}

	@Override
	public List<MyBooking> viewBookingsByCustomerId(int customerId) {

		List<MyBooking> customerBookings = new ArrayList<>();

		if (customerRepository.findById(customerId).isEmpty()) {

			throw new CustomerNotPresentException("Customer not exist!");

		}

		else {

			Customer customer = customerRepository.findById(customerId).get();

			List<MyBooking> bookings = myBookingRepository.findAll();

			for (MyBooking myBooking : bookings) {

				if (customer.equals(myBooking.getCustomer())) {

					customerBookings.add(myBooking);
				}
			}
		}

		if (customerBookings.isEmpty() || customerBookings == null) {

			throw new BookingNotFoundException("No booking found for customer");

		} else {

			return customerBookings;

		}
	}

	@Override
	public String authoriseCustomer(AuthoriseUser user) {

		AuthoriseUser updatedAuthoriseUser = new AuthoriseUser();
		updatedAuthoriseUser.setEmail(user.getEmail());
		updatedAuthoriseUser.setPassword(user.getPassword());
		updatedAuthoriseUser.setRole(user.getRole());
		
		boolean status = false;

		if (loginRepository.findAll().isEmpty()) {
			throw new CustomerNotPresentException("No users found");

		} else {
				List<Login> logins =  loginRepository.findAll();
				
				for (Login login : logins) {
					if (user.getEmail().equals(login.getEmail()) && user.getPassword().equals(login.getPassword()) && user.getRole().toLowerCase().equals(login.getRole().toLowerCase())) {
						status = true;
					}
				}
			
		}

		if (status == true)
			return "success";
		else {
			return "failed";
		}

	}

	@Override
	public List<Block> viewAllBlocksByShoppingMallId(int shoppingMallId) {

		List<Block> shoppingBlocks = new ArrayList<>();

		if (blockRepository.findAll().isEmpty()) {
			throw new NoSuchBlockExistsException("No block found");
		}

		else {

			for (Block block : blockRepository.findAll()) {

				if (block.getMall().equals(mallRepository.findById(shoppingMallId).get())) {
					shoppingBlocks.add(block);

				}
			}
		}
		if (shoppingBlocks.isEmpty()) {
			throw new NoSuchBlockExistsException("Block is not availble for this Mall");
		} else {
			return shoppingBlocks;
		}

	}

	@Override
	public List<Slot> viewAllSlotsByBlockId(int blockId) {
		List<Slot> availableSlots = new ArrayList<>();
		if (blockRepository.findAll().isEmpty()) {
			throw new NoSuchBlockExistsException("No block found");
		}

		else {

			for (Slot slot : slotRepository.findAll()) {

				if (slot.getBlock().equals(blockRepository.findById(blockId).get())) {
					availableSlots.add(slot);

				}
			}
		}

		if (availableSlots.isEmpty()) {
			throw new SlotNotAvailableException("Slots are not availble for this block");
		} else {
			return availableSlots;
		}

	}
	
	public List<Login> showAllLogin(){
		return loginRepository.findAll();
	}

}
