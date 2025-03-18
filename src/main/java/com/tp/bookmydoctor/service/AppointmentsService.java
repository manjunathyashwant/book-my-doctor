package com.tp.bookmydoctor.service;

import com.tp.bookmydoctor.requestdto.AppoinmentRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;

import jakarta.validation.Valid;

public interface AppointmentsService {

	AppointmentResponseDto bookAppointment(@Valid AppoinmentRequestDto dto);
}
