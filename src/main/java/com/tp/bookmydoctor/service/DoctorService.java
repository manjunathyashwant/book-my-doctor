package com.tp.bookmydoctor.service;

import java.util.List;

import com.tp.bookmydoctor.requestdto.DoctorRequestDto;
import com.tp.bookmydoctor.requestdto.LeaveRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;

public interface DoctorService {

	DoctorResponseDto addDoc(DoctorRequestDto reqDto);

	

	DoctorResponseDto loginDoc(DoctorRequestDto dto);



	List<AppointmentResponseDto> getAll(Integer id);
	

    LeaveResponseDto requestLeave(LeaveRequestDto leaveRequestDto);
}
