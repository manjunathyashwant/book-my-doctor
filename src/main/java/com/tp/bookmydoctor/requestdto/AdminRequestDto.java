package com.tp.bookmydoctor.requestdto;

import java.util.List;

import com.tp.bookmydoctor.entity.Doctor;

import lombok.Data;

@Data
public class AdminRequestDto {

	private Integer id;

	private String email;

	private String password;
	private List<DoctorRequestDto>doctorRequestDtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DoctorRequestDto> getDoctorRequestDtos() {
		return doctorRequestDtos;
	}

	public void setDoctorRequestDtos(List<DoctorRequestDto> doctorRequestDtos) {
		this.doctorRequestDtos = doctorRequestDtos;
	}
}
