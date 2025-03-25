package com.doctorservice.service;

import java.util.List;

import com.doctorservice.entity.Doctor;
import com.doctorservice.enums.Status;
import com.doctorservice.request.DoctorRequestDto;
import com.doctorservice.response.DoctorResponseDto;

public interface DoctorService {

	 DoctorResponseDto save(DoctorRequestDto doctorRequestDto);

//	 DoctorResponseDto update(DoctorRequestDto doctorRequestDto);

	 DoctorResponseDto delete(DoctorRequestDto doctorRequestDto);
	 List<DoctorResponseDto> getAll(int page, int size, String sortBy, String sortDir);

	List<DoctorResponseDto> searchDoctors(String doctorName, String speciaList, Double minRating);

	DoctorResponseDto rateTheDoctor(Double rating, String doctorEmail);
}
