package com.doctorservice.exception;

public class NoAppointmentsFound extends RuntimeException {
public NoAppointmentsFound(String message) {
	super(message);
}
}
