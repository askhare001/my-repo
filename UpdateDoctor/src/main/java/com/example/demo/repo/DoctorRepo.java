package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Doctor;


public interface DoctorRepo extends JpaRepository<Doctor,Integer> {

	List<Doctor> findByHospitalId(int hospital_id);
	
	
	

} 

