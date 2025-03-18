package com.tp.bookmydoctor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tp.bookmydoctor.entity.Appointments;
import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.entity.User;
import com.tp.bookmydoctor.exception.DataFoundException;
import com.tp.bookmydoctor.repository.AppointmentsRepository;
import com.tp.bookmydoctor.repository.DoctorRepository;
import com.tp.bookmydoctor.repository.UserRepository;
import com.tp.bookmydoctor.requestdto.AppoinmentRequestDto;
import com.tp.bookmydoctor.responsedto.AppointmentResponseDto;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;
import com.tp.bookmydoctor.responsedto.UserResponseDto;

import jakarta.validation.Valid;

@Service
public class AppointmentServiceImpl implements AppointmentsService{

	@Autowired
	private AppointmentsRepository appRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private JavaMailSender sender;

	@Override
	public AppointmentResponseDto bookAppointment(@Valid AppoinmentRequestDto dto) {


	    // Validate if appointment already exists  
		
		// NEED TO VALIDATE TIME AND DATE.
		
		
	    List<Appointments> appointments = appRepo.findAppointment(dto.getUser().getUserId(), dto.getDoctor().getDoctorId());
	    for (Appointments appointment : appointments) {
	        if (appointment.getStatus().equalsIgnoreCase("booked")) {
	            throw new DataFoundException("Appointment Already Booked with Username: " +
	                appointment.getUser().getUserName() + " with Doctor Name: " + 
	                appointment.getDoctor().getDoctorName());
	        }
	    }

	    // Fetch User and Doctor from database (Ensure they exist)
	    User user = userRepo.findById(dto.getUser().getUserId())
	        .orElseThrow(() -> new DataFoundException("User not found with ID: " + dto.getUser().getUserId()));

	    Doctor doctor = doctorRepo.findById(dto.getDoctor().getDoctorId())
	        .orElseThrow(() -> new DataFoundException("Doctor not found with ID: " + dto.getDoctor().getDoctorId()));

	    // Create a new appointment entity
	    Appointments app = new Appointments();
	    BeanUtils.copyProperties(dto, app);
	    app.setDoctor(doctor);
	    app.setUser(user);
	    app.setStatus("booked");

	    // Save the appointment
	    Appointments savedAppointment = appRepo.save(app);
	    AppointmentResponseDto responseDto = new AppointmentResponseDto();
	    BeanUtils.copyProperties(savedAppointment, responseDto);

	    // Correctly map Doctor entity to DoctorResponseDto
	    DoctorResponseDto doctorDto = new DoctorResponseDto();
	    BeanUtils.copyProperties(savedAppointment.getDoctor(), doctorDto);

	    // Correctly map User entity to UserResponseDto
	    UserResponseDto userDto = new UserResponseDto();
	    BeanUtils.copyProperties(savedAppointment.getUser(), userDto);

	    responseDto.setDoctorDto(doctorDto);
	    responseDto.setUserDto(userDto);
		String appointment="Appointment Date::"+responseDto.getAppointmentDate()+" \n"+"Appointment Time::"+responseDto.getAppointmentTime()+" \n"+"Doctor name::"+responseDto.getDoctorDto().getDoctorName();
		sendEmail(responseDto.getUserDto().getEmail(),"Appointment details",appointment);

	    return responseDto;
	}
	public  void   sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		sender.send(message);
	}

}
