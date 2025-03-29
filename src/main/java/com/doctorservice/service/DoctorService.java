package com.doctorservice.service;

import java.util.List;
import java.util.Optional;

import com.applicationservice.response.DoctorResponseDto;
import com.doctorservice.entity.Doctor;
import com.doctorservice.request.DoctorRequestDto;

public interface DoctorService {

	 DoctorResponseDto save(DoctorRequestDto doctorRequestDto);

//	 DoctorResponseDto update(DoctorRequestDto doctorRequestDto);

	 DoctorResponseDto delete(DoctorRequestDto doctorRequestDto);
	 List<DoctorResponseDto> getAll(int page, int size, String sortBy, String sortDir);

	List<DoctorResponseDto> searchDoctors(String doctorName, String speciaList, Double minRating);

	DoctorResponseDto rateTheDoctor(Double rating, String doctorEmail);

	Optional<Doctor> finById(Integer id);
}
