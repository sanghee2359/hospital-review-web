package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import com.hospital.review.domain.dto.ReviewCreateRequest;
import com.hospital.review.domain.dto.ReviewCreateResponse;
import com.hospital.review.domain.dto.ReviewReadResponse;
import com.hospital.review.repository.HospitalRepository;
import com.hospital.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewService {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest dto){
        // hospital 불러오기
        Optional<Hospital> optHospital = hospitalRepository.findById(dto.getHospitalId());
        log.info("{}", dto.getHospitalId());
        // ReviewEntity 만들기
        Review review = Review.builder()
                .id(dto.getHospitalId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .patientName(dto.getUserName())
                .hospital(optHospital.get())
                .build();
        // 저장
        Review savedReview = reviewRepository.save(review);

        return ReviewCreateResponse.builder()
                .userName(savedReview.getPatientName())
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .message("리뷰 등록이 성공했습니다.")
                .build();
    }

    public Review getReview(Long id) {
        // findById()에 의해 optional이 리턴되고, 그렇기 때문에 orElseThrow로 예외처리를 할 수 있는 것이다
        // 예외처리를 여러가지로 할 수 있는 것이다.
        // optReview.isEmpty()
        // optReview.orElseThrow()
        // optReview.isPresent()

        Review review = reviewRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 id가 없습니다."));
        return review;
    }

    public List<ReviewReadResponse> findAllByHospitalId(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()-> new IllegalArgumentException("해당 id가 없습니다 !"));
        List<ReviewReadResponse> reviews = reviewRepository.findByHospital(hospital)//Hospital을 주입
                .stream().map(review -> ReviewReadResponse.builder()
                            .id(review.getId()) // review를 매개변수로 사용해 ReviewReadResponse으로 전환
                            .title(review.getTitle())
                            .content(review.getContent())
                            .patientName(review.getPatientName())
                            .hospitalName(review.getHospital().getHospitalName())
                            .build()
                ).collect(Collectors.toList()); // 이부분 다시 공부하기
        return reviews;
    }
}
