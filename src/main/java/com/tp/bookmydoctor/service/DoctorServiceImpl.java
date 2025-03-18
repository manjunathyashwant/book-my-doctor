package com.tp.bookmydoctor.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.bookmydoctor.entity.Appointments;
import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.entity.Leave;
import com.tp.bookmydoctor.exception.DataFoundException;
import com.tp.bookmydoctor.exception.InvalidCredentials;
import com.tp.bookmydoctor.exception.LeaveStatus;
import com.tp.bookmydoctor.repository.DoctorRepository;
import com.tp.bookmydoctor.repository.LeaveRepository;
import com.tp.bookmydoctor.requestdto.DoctorRequestDto;
import com.tp.bookmydoctor.requestdto.LeaveRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;
import com.tp.bookmydoctor.responsedto.UserResponseDto;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;

	@Override
	public DoctorResponseDto addDoc(DoctorRequestDto reqDto) {
		Optional<Doctor> existingDoctor = doctorRepository.findByEmail(reqDto.getEmail());

		// If doctor already exists, return null or throw an exception
		if (existingDoctor.isPresent()) {
			throw new DataFoundException("Doctor with email " + reqDto.getEmail() + " already exists.");
		}

		// Create a new Doctor entity from request DTO
		Doctor doctor = new Doctor();
		BeanUtils.copyProperties(reqDto, doctor);

		// Save the new doctor entity
		Doctor savedDoctor = doctorRepository.save(doctor);

		// Prepare response DTO
		DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
		BeanUtils.copyProperties(savedDoctor, doctorResponseDto);

		return doctorResponseDto;
	}

	@Override
	public DoctorResponseDto loginDoc(DoctorRequestDto dto) {
		Doctor doctor = doctorRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new InvalidCredentials("Wrong Email "+dto.getEmail()));

		if(doctor.getPassword().equals(dto.getPassword())) {
			DoctorResponseDto doctorResponseDto= new DoctorResponseDto();

			BeanUtils.copyProperties(doctor, doctorResponseDto);
			return doctorResponseDto;
		}


		else {
			throw new InvalidCredentials("worng Password"+dto.getPassword() );	
		}
	}

	@Override
	public List<AppointmentResponseDto> getAll(Integer Id) {
		
		List<Appointments> appointmentsByDoctorId = doctorRepository.findAppointmentsByDoctorId(Id);
		List<AppointmentResponseDto>  dtos = new ArrayList<>();
		
		for(Appointments appointment : appointmentsByDoctorId) {
			AppointmentResponseDto dto = new AppointmentResponseDto();
			
			UserResponseDto dto2 = new UserResponseDto();
			BeanUtils.copyProperties(appointment.getUser(), dto2);
			
			DoctorResponseDto dto3 = new DoctorResponseDto();
			BeanUtils.copyProperties(appointment.getDoctor(), dto3);
			
			dto.setUserDto(dto2);
			dto.setDoctorDto(dto3);
			BeanUtils.copyProperties(appointment, dto);
			
			dtos.add(dto);
			
			
		}
		
		return dtos;
	}

	@Override
	public LeaveResponseDto requestLeave(LeaveRequestDto leaveRequestDto) {
		 Doctor doctor = doctorRepository.findById(leaveRequestDto.getDoctor().getDoctorId())
	                .orElseThrow(() -> new DataFoundException("Doctor not found with ID: " + leaveRequestDto.getDoctor().getDoctorId()));

	        boolean hasPendingLeave = leaveRepository.existsByDoctorIdAndStatus(doctor.getDoctorId(), LeaveStatus.PENDING);
	        if (hasPendingLeave) {
	            throw new DataFoundException("You already have a pending leave request. Please resolve it before applying for a new leave.");
	        }

	        Optional<Leave> overlappingLeaves = leaveRepository.findLeavesByDoctorAndDateRangeAndStatus(
	                doctor.getDoctorId(),
	                leaveRequestDto.getStartDate(),
	                leaveRequestDto.getEndDate(),
	                LeaveStatus.APPROVED
	        );

	        // Need to work
	        if (overlappingLeaves.isPresent()) {
	            Leave leaveExists = overlappingLeaves.get();

	            if (!(leaveRequestDto.getEndDate().isBefore(leaveExists.getStartDate()) ||
	                    leaveRequestDto.getStartDate().isAfter(leaveExists.getEndDate()))) {
	                throw new DataFoundException("Your leave overlaps with an already approved leave from "
	                        + leaveExists.getStartDate() + " to " + leaveExists.getEndDate());
	            }
	        }

	        if (leaveRequestDto.getStartDate().isAfter(leaveRequestDto.getEndDate())) {
	            throw new DataFoundException("Start date cannot be after end date.");
	        }
	        if (leaveRequestDto.getStartDate().isBefore(LocalDate.now())) {
	            throw new DataFoundException("Leave cannot be requested for past dates.");
	        }

	       Leave leave = new Leave();
	       BeanUtils.copyProperties(leaveRequestDto, leave);
	       Doctor doctor2 = new Doctor();
	       BeanUtils.copyProperties(leaveRequestDto.getDoctor(),doctor2);
	       leave.setDoctor(doctor2);
	       
	       Leave save = leaveRepository.save(leave);
	       
	       LeaveResponseDto dto = new LeaveResponseDto();
	       
	       BeanUtils.copyProperties(save, dto);
	       DoctorResponseDto responseDto = new DoctorResponseDto();
	       BeanUtils.copyProperties(save.getDoctor(), responseDto);
	       dto.setDoctor(responseDto);
	       
	      
		return dto;
	}


}
