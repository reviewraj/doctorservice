package com.doctorservice.request;

import com.doctorservice.enums.Gender;
import com.doctorservice.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorRequestDto {

	@Size(min = 3, max = 50, message = "length of characters must be between greater tha 3 and lessan than 50 ")
	@NotBlank(message = "username cannot be null or space")
	private String doctorName;

	private Double rating;

	@NotBlank(message = "phonenumber cannot be null or space")
	@Pattern(regexp = "^(?:\\+91|91|0)?[6-9]\\d{9}$", message = "please provide valid mobile number")
	private String doctorNumber;

	@Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "please enter a valid email")
	@NotBlank(message = "email cannot be null or space")
	private String doctorEmail;
	@NotBlank(message = "speciaList type cannot be null or space")
	private String speciaList;
	@NotBlank(message = "password cannot be null or space")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,}$", message = "please use strong password")
	private String doctorPassword;
	private Gender gender;

}
