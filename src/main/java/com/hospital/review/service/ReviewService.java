package com.hospital.review.service;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import com.hospital.review.domain.dto.ReviewCreateRequest;
import com.hospital.review.domain.dto.ReviewCreateResponse;
import com.hospital.review.repository.HospitalRepository;
import com.hospital.review.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
