package com.example.demo.pojo;

import java.util.List;

public class Hospital {
	
	private int hospitalregistrationId;
	private String hospitalName;
	private String address;
	private List<Doctor> doctors;
	public Hospital(int hospitalregistrationId, String hospitalName, String address, List<Doctor> doctors) {
		super();
		this.hospitalregistrationId = hospitalregistrationId;
		this.hospitalName = hospitalName;
		this.address = address;
		this.doctors = doctors;
	}
	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getHospitalregistrationId() {
		return hospitalregistrationId;
	}
	public void setHospitalregistrationId(int hospitalregistrationId) {
		this.hospitalregistrationId = hospitalregistrationId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	@Override
	public String toString() {
		return "Hospital [hospitalregistrationId=" + hospitalregistrationId + ", hospitalName=" + hospitalName
				+ ", address=" + address + ", doctors=" + doctors + "]";
	}
	
	

}
