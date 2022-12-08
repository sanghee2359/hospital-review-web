package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.User;
import com.hospital.review.domain.Visit;
import com.hospital.review.domain.dto.VisitCreateRequest;
import com.hospital.review.domain.dto.VisitResponse;
import com.hospital.review.exception.ErrorCode;
import com.hospital.review.exception.HospitalReviewAppException;
import com.hospital.review.repository.HospitalRepository;
import com.hospital.review.repository.UserRepository;
import com.hospital.review.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public List<VisitResponse> findAllByPage(Pageable pageable) {
        Page<Visit> visits = visitRepository.findAll(pageable);

        // Visits --> VisitResponse
        return visits.stream()
                .map(Visit::toResponse)
                .collect(Collectors.toList());
    }
    public void createVisit(VisitCreateRequest dto, String userName) {
        // hospital
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.HOSPITAL_NOT_FOUNDED,
                        String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()->new HospitalReviewAppException(ErrorCode.USER_NOT_FOUNDED,
                        String.format("user : %s은 없습니다.", userName)));

        // visit
        Visit visit = Visit.builder()
                .userId(user)
                .hospitalId(hospital)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();

        // visit 저장
        visitRepository.save(visit);
    }
}
