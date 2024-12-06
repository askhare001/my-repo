package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.Customer;
@Service
public class CustomerService {
	
	private List<Customer> customers=new ArrayList<Customer>();
	
	Customer customerArray[]= {new Customer(1L,"customer1","customer1@gmail.com"),
			new Customer(2L,"customer2","customer2@gmail.com"),
			new Customer(3L,"customer3","customer3@gmail.com")};
	
    public CustomerService() {
        for (Customer customer : customerArray) {
            customers.add(customer);
        }
    }
	
    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Optional<Customer> getCustomerById(long id) {
         for (Customer customer : customers) {
            if (customer.getId() == id) {
                return Optional.of(customer);
            }
        }
        return Optional.empty(); 
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }
    
    
    public Customer deleteCustomer(long id) {
        Optional<Customer> customerToDelete = getCustomerById(id);
        if (customerToDelete.isPresent()) {
            customers.remove(customerToDelete.get());
            return customerToDelete.get();
        }
        return null;
    }
    
    public String update(Long id, Customer customer) {
        Optional<Customer> exCustomer = getCustomerById(id);
        if (exCustomer.isPresent()) {
            Customer existingCustomer = exCustomer.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            return "Customer updated successfully.";
        }
        return "Customer with ID " + id + " not found."; 
    }
}
