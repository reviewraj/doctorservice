package com.doctorservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.applicationservice.response.DoctorResponseDto;
import com.doctorservice.entity.Doctor;
import com.doctorservice.enums.IsWorking;
import com.doctorservice.enums.Role;
import com.doctorservice.enums.Status;
import com.doctorservice.exception.DoctorAlreadyExists;
import com.doctorservice.exception.NoDoctorsAvailable;
import com.doctorservice.exception.PasswordMismatch;
import com.doctorservice.exception.UserNotExist;
import com.doctorservice.repository.DoctorRepository;
import com.doctorservice.request.DoctorRequestDto;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DoctorRepository doctorRepository;
//	@Autowired
//	private LeaveRepository leaveRepository;

	@Override
	public DoctorResponseDto save(DoctorRequestDto doctorRequestDto) {
		Optional<Doctor> byDoctorEmail = doctorRepository.findByDoctorEmail(doctorRequestDto.getDoctorEmail());
		if (byDoctorEmail.isEmpty()) {
			Doctor doctor = new Doctor();
			modelMapper.map(doctorRequestDto, doctor);
			doctor.setRole(Role.DOCTOR);
			Doctor save = doctorRepository.save(doctor);
			DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
			modelMapper.map(save,doctorResponseDto );
			return doctorResponseDto;

		}
		else {
			throw new DoctorAlreadyExists("doctor is already exists with the email "+doctorRequestDto.getDoctorEmail());
		}
		
		
	}
	@Override
	public DoctorResponseDto delete(DoctorRequestDto doctorRequestDto) {
		Optional<Doctor> optional = doctorRepository.findByDoctorEmail(doctorRequestDto.getDoctorEmail());
		if (optional.isPresent())
			throw new UserNotExist("doctor not exits with this email : " + doctorRequestDto.getDoctorEmail());

		else if (!optional.get().getDoctorPassword().equals(doctorRequestDto.getDoctorPassword())) {
			throw new PasswordMismatch("please enter associated password with : " + doctorRequestDto.getDoctorEmail());
		}
		Doctor userEntity = optional.get();
		userEntity.setStatus(Status.INACTIVE);
		Doctor dbUser = doctorRepository.save(userEntity);
		DoctorResponseDto userResponseDto = new DoctorResponseDto();
		modelMapper.map(dbUser, userResponseDto);
		return userResponseDto;
	}

	@Override
	public List<DoctorResponseDto> getAll(int page, int size, String sortBy, String sortDir) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
//	    Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
	    List<Doctor> doctorList = doctorRepository.findAll();
//		List<Doctor> doctorList = doctorPage.getContent();
		LocalDate today = LocalDate.now();
		if(doctorList.isEmpty()) throw new NoDoctorsAvailable("no doctors available Now ");
		doctorList = doctorList.stream().filter(doctor->doctor.getStatus()==Status.ACTIVE&&doctor.getIsWorking()==IsWorking.TRUE).toList();
		List<DoctorResponseDto> doctorResponseDtos= new ArrayList<>();
		for (Doctor doctor : doctorList) {
			DoctorResponseDto doctorRequestDto2 = new DoctorResponseDto();
			modelMapper.map(doctor, doctorRequestDto2);
			doctorResponseDtos.add(doctorRequestDto2);
		}
		
		return doctorResponseDtos;
	}

	@Override
	public List<DoctorResponseDto> searchDoctors(String doctorName, String speciaList, Double minRating) {
		 List<Doctor> searchDoctors = doctorRepository.searchDoctors(doctorName, speciaList, minRating);
		 LocalDate today = LocalDate.now();
		 searchDoctors = searchDoctors.stream().filter(doctor->doctor.getStatus()==Status.ACTIVE&&doctor.getIsWorking()==IsWorking.TRUE).toList();
		 if(searchDoctors.isEmpty()) throw new NoDoctorsAvailable("no doctors available Now ");
		 List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();
         for(Doctor doc:searchDoctors) {
        	 DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        	 modelMapper.map(doc, doctorResponseDto);
        	 doctorResponseDtos.add(doctorResponseDto);
         }
         return doctorResponseDtos;
	}

	@Override
	public DoctorResponseDto rateTheDoctor(Double rating, String doctorEmail) {
		Optional<Doctor> byDoctorEmail = doctorRepository.findByDoctorEmail(doctorEmail);
		if(byDoctorEmail.isEmpty())throw new NoDoctorsAvailable("no doctors found Now with this email : "+doctorEmail);
	 byDoctorEmail.get().addRating(rating);
	 Doctor doctor = byDoctorEmail.get();
	 Doctor save = doctorRepository.save(doctor);
	 DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		modelMapper.map(save,doctorResponseDto );
		return doctorResponseDto;
	}
//	private boolean isDoctorOnLeave(Doctor doctor, LocalDate today) {
////	    return leaveRepository.existsByDoctorAndStartingDateLessThanEqualAndEndingDateGreaterThanEqualAndLeaveStatus(
////	        doctor, today, today, AppointmentStatus.ACCEPTED); 
//		return true;
//	}
	@Override
//	@CircuitBreaker(name = "myCircuitBreakerforDoctor", fallbackMethod = "fallbackDoctor")
//	@Retry(name = "retryforDoctor", fallbackMethod = "fallbackDoctor")
//	@TimeLimiter(name = "timelimitforDoctor")
	public Optional<Doctor> finById(Integer id) {
		 Optional<Doctor> doctor = doctorRepository.findByDoctorId(id);
		 return doctor;
	       
	       	    
	}

//	// Fallback method must return CompletableFuture
//	public CompletableFuture<Optional<Doctor>> fallbackDoctor(Integer id, Throwable e) {
//	    Doctor doctor = new Doctor();
//	    doctor.setDoctorId(id);
//	    doctor.setDoctorName("Unavailable Doctor");
//	    return CompletableFuture.completedFuture(Optional.of(doctor));
//	}

}
