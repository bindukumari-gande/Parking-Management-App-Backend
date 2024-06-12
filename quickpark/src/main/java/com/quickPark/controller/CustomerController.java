package com.quickPark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quickPark.entity.AuthoriseUser;
import com.quickPark.entity.Block;
import com.quickPark.entity.Customer;
import com.quickPark.entity.Login;
import com.quickPark.entity.MyBooking;
import com.quickPark.entity.Payment;
import com.quickPark.entity.Slot;
import com.quickPark.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000", methods = { RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.POST })
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer c) {
		Customer customer = customerService.addCustomer(c);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") int customerId) {
		customerService.deleteCustomer(customerId);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomers(@RequestBody Customer c, @PathVariable int customerId) {
		return new ResponseEntity<Customer>(customerService.updateCustomer(c, customerId), HttpStatus.OK);
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
	}


	@PostMapping("/booking/{customerId}/{mallId}/{blockId}/{slotId}/{vehicleType}/{vehicleNo}")
	public ResponseEntity<MyBooking> addBooking(@PathVariable int customerId, @PathVariable int mallId,
			@PathVariable int blockId, @PathVariable int slotId, @PathVariable int vehicleType,
			@PathVariable String vehicleNo) {
		
		return new ResponseEntity<MyBooking>(
				customerService.addBooking(customerId, mallId, blockId, slotId, vehicleType, vehicleNo), HttpStatus.OK);
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<Payment> checkout(int customerId){
		return new ResponseEntity<Payment>(customerService.checkout(customerId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/myBooking/viewAllMyBookings")
	public ResponseEntity<List<MyBooking>> viewAllMyBookings() {
		return new ResponseEntity<List<MyBooking>>(customerService.viewAllMyBookings(), HttpStatus.OK);

	}

	@GetMapping("myBooking/{customerId}")
	public ResponseEntity<List<MyBooking>> viewAllBookingsByCustomer(@PathVariable int customerId) {
		return new ResponseEntity<List<MyBooking>>(customerService.viewBookingsByCustomerId(customerId), HttpStatus.OK);

	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody AuthoriseUser user) {
		return new ResponseEntity<String>(customerService.authoriseCustomer(user), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("{mallId}/selectedBlocks")
	public ResponseEntity<List<Block>> viewBlockByMallIId(@PathVariable int mallId){
		return new ResponseEntity<List<Block>>(customerService.viewAllBlocksByShoppingMallId(mallId),HttpStatus.OK);
	}

	@GetMapping("slots/{blockId}")
	public ResponseEntity<List<Slot>> viewSlotsByBlockId(@PathVariable int blockId){
		return new ResponseEntity<List<Slot>>(customerService.viewAllSlotsByBlockId(blockId),HttpStatus.OK);
	}
	
	@GetMapping("allLogins")
	public ResponseEntity<List<Login>> getAllLogin(){
		return new ResponseEntity<List<Login>>(customerService.showAllLogin(),HttpStatus.OK);
	}
}
