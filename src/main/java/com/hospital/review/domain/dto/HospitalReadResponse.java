package com.hospital.review.domain.dto;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class HospitalReadResponse { // manyToOne, OneToMany 양방향 매핑으로 현재 발생하는 loop를 막기 위해 dto 생성
    private Long id;
    private String hosptialName;
    private String roadNameAddress;
    private List<ReviewReadResponse> reviews;

    public static HospitalReadResponse fromEntity(Hospital hospital) {
        List<ReviewReadResponse> reviews = new ArrayList<>();
        for(Review review : hospital.getReviews()){
            reviews.add(ReviewReadResponse.fromEntity(review));
        }
        return HospitalReadResponse.builder()
                .id(hospital.getId())
                .hosptialName(hospital.getHospitalName())
                .roadNameAddress(hospital.getRoadNameAddress())
                .reviews(hospital.getReviews().stream()
                        .map(review-> ReviewReadResponse.fromEntity(review)).collect(Collectors.toList())) // review를 ReviewReadResponse로
                .build();
    }
}
