package com.tp.bookmydoctor.service;

import java.util.List;

import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.requestdto.UserRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;
import com.tp.bookmydoctor.responsedto.UserResponseDto;

import jakarta.validation.Valid;

public interface UserService {
    public UserResponseDto registerUser(UserRequestDto requestDto);

	public UserResponseDto login(@Valid UserRequestDto dto);

	public List<DoctorResponseDto> getAllDoc(int pageno, int pagesize, String fieldName, String sortDirection);

	public List<DoctorResponseDto> findBy(String name);

	public List<AppointmentResponseDto> getAllBooking(@Valid UserRequestDto dto);

	
}
