package com.doctorservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doctorservice.entity.Doctor;
import com.doctorservice.enums.Status;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	Optional<Doctor>  findByDoctorEmail(String doctorEmail);
	@Query("SELECT d FROM Doctor d WHERE "
            + "(:doctorName IS NULL OR d.doctorName LIKE %:doctorName%) "
            + "AND (:speciaList IS NULL OR d.speciaList LIKE %:speciaList%) "
            + "AND (:minRating IS NULL OR d.rating >= :minRating)")
    List<Doctor> searchDoctors(
        @Param("doctorName") String doctorName,
        @Param("speciaList") String speciaList,
        @Param("minRating") Double minRating
    );
}
