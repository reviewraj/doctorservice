package com.doctorservice.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.applicationservice.response.ResponseDto;

@FeignClient(value = "APPLICATIONSERVICE", path = "/bookmydoctor/api/appointment")
public interface Applicationfeignnconfig {
	@GetMapping("/getByDocterId")
	public ResponseEntity<ResponseDto> getByDocterId(@RequestParam Integer UserId );

}
