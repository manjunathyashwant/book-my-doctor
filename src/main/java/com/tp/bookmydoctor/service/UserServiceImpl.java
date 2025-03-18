package com.tp.bookmydoctor.service;

import com.tp.bookmydoctor.entity.Appointments;
import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.entity.User;
import com.tp.bookmydoctor.exception.DataFoundException;
import com.tp.bookmydoctor.exception.InvalidCredentials;
import com.tp.bookmydoctor.repository.AppointmentsRepository;
import com.tp.bookmydoctor.repository.DoctorRepository;
import com.tp.bookmydoctor.repository.UserRepository;
import com.tp.bookmydoctor.requestdto.UserRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;
import com.tp.bookmydoctor.responsedto.UserResponseDto;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private AppointmentsRepository appRepo;




	// @Transactional
	public UserResponseDto registerUser(UserRequestDto requestDto) {  

		Optional<User> byEmail = userRepo.findByEmail(requestDto.getEmail());
		if (byEmail.isPresent()) {
			throw new DataFoundException("user is already present with the given eamil::" + requestDto.getEmail() + " please enter different email");
		} else {
			User user = new User();
			BeanUtils.copyProperties(requestDto, user);
			userRepo.save(user);
			UserResponseDto responseDto = new UserResponseDto();
			BeanUtils.copyProperties(user, responseDto);
			System.out.println(responseDto);
			return responseDto;
		}
	}







	@Override
	public UserResponseDto login(@Valid UserRequestDto dto) {

		User user = userRepo.findByEmail(dto.getEmail()).orElseThrow(() -> new DataFoundException("Doctor Does not Exhist with email :"+dto.getEmail()));

		if(user.getPassword().equals(dto.getPassword())) {


			UserResponseDto dto2 = new UserResponseDto();
			BeanUtils.copyProperties(user, dto2);
			return dto2;
		}

		else {
			throw new InvalidCredentials("Wrong Password");
		}
	}
	@Override
	public List<DoctorResponseDto> getAllDoc(int pageno, int pagesize, String fieldName, String sortDirection) {

		// Fix sorting typo and allow sorting by multiple fields
		Sort sort = sortDirection.equalsIgnoreCase("ascending")
				? Sort.by(fieldName).ascending().and(Sort.by("speciality").ascending())
				: Sort.by(fieldName).descending().and(Sort.by("speciality").descending());

		Pageable pageable = PageRequest.of(pageno, pagesize, sort);

		// Fetch only working doctors with pagination
		Page<Doctor> pagedDoctors = doctorRepo.findAllByWorkingStatusTrue(pageable); // Ensure this method exists

		List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();

		for (Doctor doctor : pagedDoctors.getContent()) {
			DoctorResponseDto dto = new DoctorResponseDto();
			dto.setDoctorName(doctor.getDoctorName());
			dto.setSpeciality(doctor.getSpeciality());
			dto.setEmail(doctor.getEmail());
			dto.setRatings(doctor.getRatings());

			// Debugging step: Check if properties are copied
			System.out.println("Converted DTO: " + dto);

			doctorResponseDtos.add(dto);
		}

		return doctorResponseDtos;
	}














	@Override
	public List<DoctorResponseDto> findBy(String name) {
		List<Doctor> byDoctorNameOrSpeciality = doctorRepo.findByDoctorNameOrSpeciality(name);
		List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();

		for (Doctor doctor : byDoctorNameOrSpeciality) {
			DoctorResponseDto dto = new DoctorResponseDto();
			BeanUtils.copyProperties(doctor, dto);


			doctorResponseDtos.add(dto);
		}

		return doctorResponseDtos;
	}







	@Override
	public List<AppointmentResponseDto> getAllBooking(@Valid UserRequestDto dto) {

		List<Appointments> appointmentsByUserId = appRepo.findAppointmentsByUserId(dto.getUserId());

		List<AppointmentResponseDto> dtos = new ArrayList<>();

		for(Appointments appointments : appointmentsByUserId) {
			AppointmentResponseDto dto1 = new AppointmentResponseDto();

			BeanUtils.copyProperties(appointments, dto1);

			Doctor doctor = appointments.getDoctor();
			DoctorResponseDto responseDto = new DoctorResponseDto();

			BeanUtils.copyProperties(doctor, responseDto);

			dto1.setDoctorDto(responseDto);

			dtos.add(dto1);

		}
		return dtos;

	}



}
