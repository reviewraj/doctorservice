package com.doctorservice.exception;

public class NoDoctorsAvailable extends RuntimeException {

	public NoDoctorsAvailable(String message) {
		super(message);
	}

}
