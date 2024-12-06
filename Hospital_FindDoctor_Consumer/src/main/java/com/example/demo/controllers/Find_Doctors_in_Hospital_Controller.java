package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojos.Doctor;
import com.example.demo.pojos.Hospital;
//import com.example.demo.repo.HospitalRepository;

@RestController
public class Find_Doctors_in_Hospital_Controller {

//	@Autowired
//	HospitalRepository repo;

	List<Doctor> doctors_received = new ArrayList<>();

	@Bean
	public Consumer<String> readDoctors() {
		System.out.println("***********received doctor details**********");

		return (doctor) -> {
			System.out.println("Inside the consumer");
//			doctors_received.add(doctor);
			System.out.println("Consumer Received : " + doctor);
		};
	}

	@GetMapping("/hospitals/{hospitalId}")
	ResponseEntity<Hospital> findAllDoctorsInHospitals(@PathVariable int hospitalId) {
		List<Doctor> doctors = new ArrayList<>();
		Hospital hospital = new Hospital() ;
//		if (hospital != null) {
//			List<Integer> doctor_ids = repo.findDoctorIds(hospitalId);
//			for (int i = 0; i < doctor_ids.size(); i++) {
//				for (Doctor d : doctors_received) {
//					if (d.getDoctorId() == doctor_ids.get(i)) {
//						doctors.add(d);
//					}
//				}
//			}

			hospital.setDoctors(doctors_received);

			return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);

//		}
//		return new ResponseEntity<Hospital>(HttpStatus.NO_CONTENT);
	}

}