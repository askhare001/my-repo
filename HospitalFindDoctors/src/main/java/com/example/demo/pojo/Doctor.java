package com.example.demo.pojo;

public class Doctor {
	

	private int doctorId;
	private String doctorName;
	private String spec;
	private int hospitalId;

	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", spec=" + spec + ", hospitalId="
				+ hospitalId + "]";
	}
	public Doctor(int doctorId, String doctorName, String spec, int hospitalId) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.spec = spec;
		this.hospitalId = hospitalId;
	}

	
	

}
