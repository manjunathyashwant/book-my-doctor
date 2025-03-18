package com.tp.bookmydoctor.requestdto;

import java.util.List;

import com.tp.bookmydoctor.entity.Leave;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DoctorRequestDto {
	
	 
	    private Integer doctorId;
	  
	    @NotBlank(message = "this field cannot be blank")
	    @Size(min = 3,max = 40)
	    private String doctorName;
	    @Column(length = 50)
	    @NotBlank(message = "this field cannot be blank")
	    private String speciality;
	    @Email
	    private String email;
	    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
	    private String password;
	    private Double ratings;
	    
	    private Boolean working;
	    
	    private List<LeaveRequestDto> leaves;

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

	public List<LeaveRequestDto> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeaveRequestDto> leaves) {
		this.leaves = leaves;
	}
}
