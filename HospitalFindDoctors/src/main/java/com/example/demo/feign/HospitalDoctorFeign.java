package com.example.demo.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.pojo.Doctor;

@FeignClient(name="find-doctor-all")//,url="http://localhost:8084")
public interface HospitalDoctorFeign {
	
	
	@GetMapping("/doctors/{hospital_id}")
	public ResponseEntity <List<Doctor>> findAllDoctorsByHospitalId(@PathVariable int hospital_id);
	
}
