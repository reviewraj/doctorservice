package com.doctorservice.response;

import com.doctorservice.enums.Gender;
import com.doctorservice.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data
public class DoctorResponseDto {
	    private Integer doctorId;

	    private String doctorName;

	    private Double rating;

	    private String doctorNumber;


	    private String speciaList;


	    private Role role;


	    @Enumerated(EnumType.STRING)
	    private Gender gender;

	    private Integer ratingCount = 0; 

}
