package com.doctorservice.exception;

public class DoctorAlreadyExists extends RuntimeException {
public DoctorAlreadyExists(String message) {
	super(message);
}
}
