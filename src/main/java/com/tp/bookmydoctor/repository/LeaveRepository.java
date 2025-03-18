package com.tp.bookmydoctor.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tp.bookmydoctor.entity.Doctor;
import com.tp.bookmydoctor.entity.Leave;
import com.tp.bookmydoctor.exception.LeaveStatus;


public interface LeaveRepository extends JpaRepository<Leave, Integer>{
	

    List<Leave> findByDoctor(Doctor doctor);

    @Query("SELECT l FROM Leave l WHERE l.doctor.id = :doctorId AND l.startDate >= :startDate AND l.endDate <= :endDate AND l.status IN (:statuses)")
    Optional<Leave> findLeavesByDoctorAndDateRangeAndStatus(
            @Param("doctorId") Integer doctorId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("statuses") LeaveStatus statuses
    );

    @Query("SELECT COUNT(l) > 0 FROM Leave l WHERE l.doctor.id = :doctorId AND l.status = :status")
    boolean existsByDoctorIdAndStatus(@Param("doctorId") Integer doctorId, @Param("status") LeaveStatus status);

	


}
