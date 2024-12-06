package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Doctor;
import com.example.demo.repo.DoctorRepo;

@RestController
public class FindDoctorController {

	@Autowired
	DoctorRepo doctorRepo;
	
	@GetMapping("/doctors")
	List<Doctor>findAllDoctor(@RequestHeader (name="sort", defaultValue="all") String sort) 
	
	
	{
		List<Doctor> optional= doctorRepo.findAll();
		System.out.println("request header     "+sort);
		return optional;
		
		
		
	}
	
	@GetMapping("/doctors/{hospital_id}")
	public ResponseEntity <List<Doctor>> findAllDoctorsByHospitalId(@PathVariable int hospital_id)
	{
		
		        List<Doctor> doctors=new ArrayList<>();
				
				doctors=doctorRepo.findByHospitalId(hospital_id);
				
				if (doctors.size()>0)
				{
					
					return new ResponseEntity <List<Doctor>>(doctors,HttpStatus.OK);
				}
		
				return new ResponseEntity <List<Doctor>>(HttpStatus.NO_CONTENT);
		
	}
}
