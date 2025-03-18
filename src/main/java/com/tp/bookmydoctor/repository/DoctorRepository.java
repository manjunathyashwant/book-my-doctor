package com.tp.bookmydoctor.repository;

import com.tp.bookmydoctor.entity.Appointments;
import com.tp.bookmydoctor.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
	Optional<Doctor> findByEmail(String email);


	@Query("SELECT d FROM Doctor d WHERE d.working = true")
	List<Doctor> findAllWorkingDoctors();

	@Query("SELECT d FROM Doctor d WHERE LOWER(d.doctorName) LIKE LOWER(CONCAT('%', :searchText, '%')) " +
	           "OR LOWER(d.speciality) LIKE LOWER(CONCAT('%', :searchText, '%'))")
	    List<Doctor> findByDoctorNameOrSpeciality(@Param("searchText") String searchText);
	
	
	
	@Query("SELECT d.appointmentsList FROM Doctor d WHERE d.doctorId = :doctorId")
    List<Appointments> findAppointmentsByDoctorId(@Param("doctorId") Integer doctorId);


	@Query("SELECT d FROM Doctor d WHERE d.working = true")
	Page<Doctor> findAllByWorkingStatusTrue(Pageable pageable);
}
	

