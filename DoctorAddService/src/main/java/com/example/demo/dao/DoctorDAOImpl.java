package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.exception.DoctorAlreadyExistsException;
import com.example.demo.pojo.Doctor;

@Repository
public class DoctorDAOImpl implements DoctorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addDoctor(Doctor doctor) {
        int added = 0;
        String insertDoc = "INSERT INTO doctor_new (doctor_id, doctor_name, specialization, hospital_id) VALUES (?, ?, ?, ?)";

        try {
            added = jdbcTemplate.update(
                insertDoc, 
                doctor.getDoctorId(),
                doctor.getDoctorName(),
                doctor.getSpec(),
                doctor.getHospitalId()
            );
        } catch (Exception e) {
            System.err.println("Error adding doctor: " + e.getMessage());
            throw new DoctorAlreadyExistsException("Record exists!");
        }
        return added;
    }
}
