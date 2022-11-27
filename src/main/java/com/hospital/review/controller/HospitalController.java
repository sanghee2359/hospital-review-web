package com.hospital.review.controller;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.dto.HospitalReadResponse;
import com.hospital.review.domain.dto.ReviewCreateRequest;
import com.hospital.review.domain.dto.ReviewCreateResponse;
import com.hospital.review.domain.dto.ReviewReadResponse;
import com.hospital.review.service.HospitalService;
import com.hospital.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    // api 설계
    // POST /api/v1/hospitals/{id}/reviews
    @PostMapping("/{id}/reviews") // client에게 받은 '저장'요청을 service로 보내주고, service에서 return한 것을 user에게 response한다.
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }
    // GET /api/v1/hospitals/{hospitalId}/reviews
    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviewList(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }

    // GET /api/v1/hospitals/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> hospital(@PathVariable Long id){
        Hospital hospital = hospitalService.findById(id);
                // dto로 매핑하는 로직 -> controller에서 하기
        HospitalReadResponse hospitalReadResponse = HospitalReadResponse.fromEntity(hospital);
        return ResponseEntity.ok().body(hospitalReadResponse);
    }
}
