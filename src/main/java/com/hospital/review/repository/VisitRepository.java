package com.hospital.review.repository;

import com.hospital.review.domain.Disease;
import com.hospital.review.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
