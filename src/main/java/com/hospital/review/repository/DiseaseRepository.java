package com.hospital.review.repository;

import com.hospital.review.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, String> {
    Optional<Disease> findByCode(String code);
}
