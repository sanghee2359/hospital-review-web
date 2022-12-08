package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import com.hospital.review.domain.User;
import com.hospital.review.domain.Visit;
import com.hospital.review.domain.dto.ReviewReadResponse;
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
    public List<VisitResponse> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("해당 id의 user이 없습니다 !"));
        List<VisitResponse> visitList = visitRepository.findByUserId(user)//Hospital을 주입
                .stream().map(visit -> VisitResponse.builder()
                        .hospitalName(visit.toResponse().getHospitalName()) // review를 매개변수로 사용해 ReviewReadResponse으로 전환
                        .userName(visit.toResponse().getUserName())
                        .disease(visit.toResponse().getDisease())
                        .amount(visit.toResponse().getAmount())
                        .build()
                ).collect(Collectors.toList()); // 이부분 다시 공부하기
        return visitList;
    }

    public List<VisitResponse> findAllByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new IllegalArgumentException("해당 id의 hospital이 없습니다 !"));
        List<VisitResponse> visitList = visitRepository.findByHospitalId(hospital)//Hospital을 주입
                .stream().map(visit -> VisitResponse.builder()
                        .hospitalName(visit.toResponse().getHospitalName()) // review를 매개변수로 사용해 ReviewReadResponse으로 전환
                        .userName(visit.toResponse().getUserName())
                        .disease(visit.toResponse().getDisease())
                        .amount(visit.toResponse().getAmount())
                        .build()
                ).collect(Collectors.toList()); // 이부분 다시 공부하기

        return visitList;
    }

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
