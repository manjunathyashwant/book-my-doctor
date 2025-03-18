package com.tp.bookmydoctor.responsedto;

import java.util.Date;

import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class AppointmentResponseDto {


	private Integer appointmentId;
	private String appointmentDate;

	private String appointmentTime;

	private String status;


	private UserResponseDto userDto;

	private DoctorResponseDto doctorDto;

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserResponseDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserResponseDto userDto) {
		this.userDto = userDto;
	}

	public DoctorResponseDto getDoctorDto() {
		return doctorDto;
	}

	public void setDoctorDto(DoctorResponseDto doctorDto) {
		this.doctorDto = doctorDto;
	}
}
