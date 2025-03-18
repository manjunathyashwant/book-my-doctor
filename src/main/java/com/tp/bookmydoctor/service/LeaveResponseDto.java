package com.tp.bookmydoctor.service;

import java.time.LocalDate;

import com.tp.bookmydoctor.exception.LeaveStatus;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;

import lombok.Data;

@Data
public class LeaveResponseDto {
	
	 private Integer id;

	    private LocalDate startDate;

	    private LocalDate endDate;

	    private String reason;

	    private LeaveStatus status = LeaveStatus.PENDING;

	    private DoctorResponseDto doctor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public DoctorResponseDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorResponseDto doctor) {
		this.doctor = doctor;
	}
}


