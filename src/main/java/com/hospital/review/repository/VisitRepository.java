package com.hospital.review.repository;

import com.hospital.review.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByHospitalId(Hospital hospital);
    List<Visit> findByUserId(User user);
}
