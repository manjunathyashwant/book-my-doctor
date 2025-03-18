package com.tp.bookmydoctor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "doctor_table")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	@Column(length = 50)

	private String doctorName;
	@Column(length = 50)

	private String speciality;
	@Email
	@Column(nullable = false)
	private String email;

	private String password;
	private Double ratings;

	private Boolean working;



	@OneToMany(mappedBy = "doctor")
	private List<Appointments> appointmentsList;

	@OneToMany(mappedBy = "doctor")
	private List<Leave> leaves;

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
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

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public List<Appointments> getAppointmentsList() {
		return appointmentsList;
	}

	public void setAppointmentsList(List<Appointments> appointmentsList) {
		this.appointmentsList = appointmentsList;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}
}

