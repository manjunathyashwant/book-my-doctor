package com.tp.bookmydoctor.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.tp.bookmydoctor.entity.Leave;
import com.tp.bookmydoctor.responsedto.DoctorResponseDto;

import lombok.Data;

@Data
public class LeaveRequestDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private String reason;

//    private LeaveStatus status = LeaveStatus.PENDING;

    private DoctorRequestDto doctor;

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

    public DoctorRequestDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorRequestDto doctor) {
        this.doctor = doctor;
    }
}
