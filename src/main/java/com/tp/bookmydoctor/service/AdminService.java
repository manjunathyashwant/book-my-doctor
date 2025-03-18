package com.tp.bookmydoctor.service;

import java.util.List;

import com.tp.bookmydoctor.requestdto.AdminRequestDto;
import com.tp.bookmydoctor.responsedto.AdminResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;

public interface AdminService {

	AdminResponseDto login(AdminRequestDto dto);

	List<DoctorResponseDto> getAllDoc(AdminRequestDto dto);

}
