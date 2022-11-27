package com.hospital.review.domain.dto;

import com.hospital.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReviewReadResponse {
    private Long id;
    private String title;
    private String content;
    private String patientName;
    private String hospitalName;

    public static ReviewReadResponse fromEntity(Review review) {
        ReviewReadResponse response = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .patientName(review.getPatientName()) // review를 ReviewReadResponse로
                .hospitalName(review.getHospital().getHospitalName()) // review를 ReviewReadResponse로
                .build();
        return response;
    }
}
