package com.example.demo.exception;

public class DoctorAlreadyExistsException extends RuntimeException {
	
	private String message;
	
	public DoctorAlreadyExistsException()
	{
		this("");
	}
	
	public DoctorAlreadyExistsException(String message)
	{
		super(message);
	}

}
