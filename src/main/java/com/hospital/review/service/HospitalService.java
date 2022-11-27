package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public Hospital findById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id가 없습니다."));
        return hospital;
    }
}
