package com.tp.bookmydoctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tp.bookmydoctor.requestdto.AppoinmentRequestDto;
import com.tp.bookmydoctor.requestdto.UserRequestDto;
import com.tp.bookmydoctor.responsedto.GlobalResponseDto;
import com.tp.bookmydoctor.service.AppointmentsService;

import jakarta.validation.Valid;

@RestController
public class AppointmentsController {

	@Autowired
	public AppointmentsService appointmentsService;


	@PostMapping("/bookappoint")
	public GlobalResponseDto bookAppointment(@RequestBody @Valid AppoinmentRequestDto dto) {
		return new GlobalResponseDto(false, "Appointment Booked Successfully", appointmentsService.bookAppointment(dto));
	}
}
