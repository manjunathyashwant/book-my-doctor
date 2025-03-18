package com.tp.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tp.bookmydoctor.requestdto.AdminRequestDto;
import com.tp.bookmydoctor.responsedto.GlobalResponseDto;
import com.tp.bookmydoctor.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired 
	private AdminService service;
	
	@PostMapping("loginadmin")
	public GlobalResponseDto loginAdmin(@RequestBody AdminRequestDto dto) {
		
		return new GlobalResponseDto(false, "Admin Login Successfull",service.login(dto) );
	}
	
	
	@GetMapping("/getDocs")
public GlobalResponseDto getAllDoc(@RequestBody AdminRequestDto dto) {
		
		return new GlobalResponseDto(false, "Admin Login Successfull",service.getAllDoc(dto));
	}
	

}
