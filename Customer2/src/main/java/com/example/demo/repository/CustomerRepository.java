package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Customer;

@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final RowMapper<Customer> rowMapper= new RowMapper <Customer> () {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Customer(rs.getLong("id"),rs.getString("name"),rs.getString("email"));
		}
		
		
		
	};
	
	public List<Customer> findAll(){
		
		return jdbcTemplate.query("SELECT * FROM customers", rowMapper);
	}
	
	public Optional<Customer> findById(Long id){
		
		return jdbcTemplate.query("SELECT * FROM customers where id=?",new Object[] {id}, rowMapper).stream().findFirst();
	}
	
	public int saveCustomer(Customer customer){
		
		return jdbcTemplate.update("INSERT INTO customers(id,name,email) VALUES (?,?,?)",customer.getId(),customer.getName(),customer.getEmail());
	}
	
public int updateCustomer(Long id,Customer customer){
		
		return jdbcTemplate.update("UPDATE customers SET name=?,email=? WHERE id=?",customer.getName(),customer.getEmail(),id);
	}

public int deleteCustomer(Long id){
	
	return jdbcTemplate.update("DELETE FROM customers WHERE id=?",id);
}



}
