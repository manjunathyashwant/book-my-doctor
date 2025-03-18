package com.tp.bookmydoctor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.bookmydoctor.entity.Admin;
import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.exception.DataFoundException;
import com.tp.bookmydoctor.exception.InvalidCredentials;
import com.tp.bookmydoctor.repository.AdminRepository;
import com.tp.bookmydoctor.repository.DoctorRepository;
import com.tp.bookmydoctor.requestdto.AdminRequestDto;
import com.tp.bookmydoctor.responsedto.AdminResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repo;

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public AdminResponseDto login(AdminRequestDto dto) {

		Admin orElseThrow = repo.findById(dto.getId()).orElseThrow( () -> new DataFoundException("Admin Not found"));

		if(orElseThrow.getPassword().equals(dto.getPassword())){

			AdminResponseDto adminResponseDto = new AdminResponseDto();

			BeanUtils.copyProperties(orElseThrow, adminResponseDto);

			return adminResponseDto;

		}

		else {
			throw new InvalidCredentials("Wrong Password");
		}
	}

	@Override
	public List<DoctorResponseDto> getAllDoc(AdminRequestDto dto) {
		List<Doctor> all = doctorRepository.findAll();

		List<DoctorResponseDto>dtos = new ArrayList<>();
		
		for(Doctor doctor : all) {
			DoctorResponseDto responseDto = new  DoctorResponseDto();
			BeanUtils.copyProperties(doctor, responseDto);
			dtos.add(responseDto);
			
		}
		return dtos;
	}



}
