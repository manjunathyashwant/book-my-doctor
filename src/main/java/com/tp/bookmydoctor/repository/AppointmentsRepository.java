package com.tp.bookmydoctor.repository;

import com.tp.bookmydoctor.entity.Appointments;
import com.tp.bookmydoctor.entity.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentsRepository extends JpaRepository<Appointments,Integer> {

	@Query("FROM Appointments a WHERE a.user.userId = :userId AND a.doctor.doctorId = :docId")
	List<Appointments> findAppointment(@Param("userId") Integer userId, @Param("docId") Integer docId);
	
	
	@Query("SELECT a FROM Appointments a WHERE a.user.userId = :userId")
    List<Appointments> findAppointmentsByUserId(@Param("userId") Integer userId);

}
