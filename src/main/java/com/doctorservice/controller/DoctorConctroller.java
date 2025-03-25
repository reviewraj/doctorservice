package com.doctorservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctorservice.request.DoctorRequestDto;
import com.doctorservice.response.ResponseDto;
import com.doctorservice.service.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@RestController
@RequestMapping("/bookmydoctor/api/doctor")
public class DoctorConctroller {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveUser(@RequestBody @Valid DoctorRequestDto doctorRequestDto) {
		return ResponseEntity
				.ok(new ResponseDto(false, "doctor data created successfully", doctorService.save(doctorRequestDto)));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteUser(@RequestBody DoctorRequestDto doctorRequestDto) {
		return ResponseEntity.ok(
				new ResponseDto(false, "doctor data  deleted successfully", doctorService.delete(doctorRequestDto)));
	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "doctorName") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {
		return ResponseEntity.ok(new ResponseDto(false, "doctors listed successfully",
				doctorService.getAll(page, size, sortBy, sortDir)));
	}

	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchDoctors(@RequestParam(required = false) String doctorName,
			@RequestParam(required = false) String speciaList, @RequestParam(required = false) Double minRating) {

		return ResponseEntity.ok(new ResponseDto(false, "please find the list below",
				doctorService.searchDoctors(doctorName, speciaList, minRating)));

	}

	@PutMapping("/rating")
	public ResponseEntity<ResponseDto> searchDoctors(@RequestParam Double rating, @RequestParam String doctorEmail) {

		return ResponseEntity.ok(
				new ResponseDto(false, "please find the list below", doctorService.rateTheDoctor(rating, doctorEmail)));

	}

}
