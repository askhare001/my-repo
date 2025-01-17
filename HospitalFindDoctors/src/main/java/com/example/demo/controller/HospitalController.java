package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.feign.HospitalDoctorFeign;
import com.example.demo.pojo.Doctor;
import com.example.demo.pojo.Hospital;
import com.example.demo.repo.HospitalRepository;
import com.netflix.spectator.impl.Config;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class HospitalController {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Autowired
	HospitalDoctorFeign hospitalDoctorFeign;
	
	@Autowired
    private RestTemplate restTemplate;

	
	@GetMapping("/hospitals/{hospitalId}")
	ResponseEntity<Hospital>findalldoctorsinhospital(@PathVariable int hospitalId) {
		
		List<Doctor> doctors=new ArrayList<>();
		
		Hospital hospital=	hospitalRepository.findHospitalById(hospitalId);
		//hospital.setDoctors(doctors);
		
		if (hospital != null) {
	 	        ResponseEntity<Doctor[]> entity = restTemplate.getForEntity(
	            "http://find-doctor-all/doctors/{hospital_id}",
	            Doctor[].class,
	            hospitalId
	        );

	        hospital.setDoctors(Arrays.asList(entity.getBody()));
	        return new ResponseEntity<>(hospital, HttpStatus.OK);
	    } 
		else {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	
}
	
    @GetMapping("/hospitals-cb/{hospitalId}")
    @CircuitBreaker(name = "doctorServiceCircuitBreaker", fallbackMethod = "fallbackForFindAllDoctorsInHospital")
    public ResponseEntity<Hospital> findAllDoctorsInHospital(@PathVariable int hospitalId) {

        Hospital hospital = hospitalRepository.findHospitalById(hospitalId);

        if (hospital != null) {
            ResponseEntity<Doctor[]> entity = restTemplate.getForEntity(
                "http://find-doctor-all/doctors/{hospital_id}",
                Doctor[].class,
                hospitalId
            );

            List<Doctor> doctors = Arrays.asList(entity.getBody());
            hospital.setDoctors(doctors);

            return new ResponseEntity<>(hospital, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<Hospital> fallbackForFindAllDoctorsInHospital(int hospitalId, Throwable throwable) {
		Hospital hospital=new Hospital();
		hospital.setAddress("addresscb");
		hospital.setHospitalName("namecb");
		hospital.setHospitalregistrationId(1);
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
    }
    
    
	@GetMapping("/hospitals-feign/{hospitalId}")
	@CircuitBreaker(name="circuit-breaker-for-doctor",fallbackMethod="myMethod")
	ResponseEntity<Hospital>findAllDoctorsInhospitalFeign(@PathVariable int hospitalId) {
		List<Doctor> doctors=new ArrayList<>();
		
		Hospital hospital=	hospitalRepository.findHospitalById(hospitalId);
	
		ResponseEntity<List<Doctor>> entity = hospitalDoctorFeign.findAllDoctorsByHospitalId(hospitalId);
        
        if (entity.getBody() != null) {
            hospital.setDoctors(entity.getBody());
        } else {
            hospital.setDoctors(new ArrayList<>()); 
        }

        return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
    

}
	
	ResponseEntity<Hospital> myMethod( int hospitalId, Throwable e)
	{
		
		Hospital hospital=new Hospital();
		hospital.setAddress("address");
		hospital.setHospitalName("name");
		hospital.setHospitalregistrationId(1);
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/hospitals-feign-retry/{hospitalId}")
	@Retry(name="retry-for-doctor",fallbackMethod="myMethod")
	ResponseEntity<Hospital>findAllDoctorsInhospitalFeignRetry(@PathVariable int hospitalId) {

		Hospital hospital=	hospitalRepository.findHospitalById(hospitalId);
	
		ResponseEntity<List<Doctor>> entity = hospitalDoctorFeign.findAllDoctorsByHospitalId(hospitalId);
        
        if (entity.getBody() != null) {
            hospital.setDoctors(entity.getBody());
        } else {
            hospital.setDoctors(new ArrayList<>()); 
        }

        return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
    

}
	
	@GetMapping("/hospitals-feign-bulkhead/{hospitalId}")
	@Bulkhead(name="bulkhead-for-doctor",fallbackMethod="bulkHead")
	ResponseEntity<Hospital>findAllDoctorsInhospitalFeignBulkhead(@PathVariable int hospitalId) {

		Hospital hospital=	hospitalRepository.findHospitalById(hospitalId);
	
		ResponseEntity<List<Doctor>> entity = hospitalDoctorFeign.findAllDoctorsByHospitalId(hospitalId);
        
        if (entity.getBody() != null) {
            hospital.setDoctors(entity.getBody());
        } else {
            hospital.setDoctors(new ArrayList<>()); 
        }

        return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
    

}
	
	@GetMapping("/hospitals-feign-ratelimiter/{hospitalId}")
	@RateLimiter(name="ratelimiter-for-doctor",fallbackMethod="ratelimiter")
	ResponseEntity<Hospital>findAllDoctorsInhospitalFeignratelimiter(@PathVariable int hospitalId) {

		Hospital hospital=	hospitalRepository.findHospitalById(hospitalId);
	
		ResponseEntity<List<Doctor>> entity = hospitalDoctorFeign.findAllDoctorsByHospitalId(hospitalId);
        
        if (entity.getBody() != null) {
            hospital.setDoctors(entity.getBody());
        } else {
            hospital.setDoctors(new ArrayList<>()); 
        }

        return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
    

}
	ResponseEntity<Hospital> ratelimiter( int hospitalId, Throwable e)
	{

		return new ResponseEntity<Hospital>( HttpStatus.TOO_MANY_REQUESTS);
		
	}
	ResponseEntity<Hospital> bulkHead( int hospitalId, Throwable e)
	{
		

		return new ResponseEntity<Hospital>( HttpStatus.TOO_MANY_REQUESTS);
		
	}
	
}


