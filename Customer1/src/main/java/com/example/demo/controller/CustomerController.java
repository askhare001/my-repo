package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Customer;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<Customer> getAllCustomers()
	{
		return customerService.getAllCustomers();
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
    	   Optional<Customer> customer = (customerService.getCustomerById(id));
           return customer.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
    	Customer savedCustomer=customerService.addCustomer(customer);
    	return new ResponseEntity<>(savedCustomer,HttpStatus.CREATED);
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id)
    {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
    	
    }
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
	    String responseMessage = customerService.update(id, customer);
	    
	 	    if (responseMessage.contains("not found")) {
	        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
	    }
	    
	    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

}
