package com.tp.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.bookmydoctor.requestdto.DoctorRequestDto;
import com.tp.bookmydoctor.requestdto.LeaveRequestDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;
import com.tp.bookmydoctor.responsedto.GlobalResponseDto;
import com.tp.bookmydoctor.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService  doctorService;
	
	@PostMapping("/adddoc")
	public GlobalResponseDto addDoc(@RequestBody DoctorRequestDto reqDto) {
		
		return new GlobalResponseDto(true, "Doctor ADDED Successfully",doctorService.addDoc(reqDto) );
		
	}
	
	@PostMapping("/logindoc")
	public GlobalResponseDto loginDoc(@RequestBody DoctorRequestDto dto) {
		return new GlobalResponseDto(true, "Login Successfull", doctorService.loginDoc(dto));
	}
	
	@PostMapping("/getallapp")
	public GlobalResponseDto getAllAppointmnet(@RequestParam Integer Id) {
		return new GlobalResponseDto(true, "All Appointments", doctorService.getAll(Id));
	}
	
	@PostMapping("applyleave")
	public GlobalResponseDto applyForLeave(@RequestBody LeaveRequestDto dto) {
		return new GlobalResponseDto(true, "Leave Applied Successfully", doctorService.requestLeave(dto));
	}
	
	
	

	
	
	
	
}
