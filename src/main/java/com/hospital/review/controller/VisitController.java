package com.hospital.review.controller;

import com.hospital.review.domain.Hospital;
import com.hospital.review.domain.Visit;
import com.hospital.review.domain.dto.HospitalReadResponse;
import com.hospital.review.domain.dto.VisitCreateRequest;
import com.hospital.review.domain.dto.VisitResponse;
import com.hospital.review.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    private final VisitService visitService;

    // GET /api/v1/hospitals/{id}
    @GetMapping("users/{id}")
    public ResponseEntity<List<VisitResponse>> FindByUserId(@PathVariable Long id){
        List<VisitResponse> visitResponse = visitService.findAllByUserId(id);
        return ResponseEntity.ok().body(visitResponse);
    }
    @GetMapping("hospitals/{id}")
    public ResponseEntity<List<VisitResponse>> FindByHospitalId(@PathVariable Long id){
        List<VisitResponse> visitResponse = visitService.findAllByHospitalId(id);
        // dto로 매핑하는 로직 -> controller에서 하기
//        VisitResponse visitResponse = Visit.toResponse();
        return ResponseEntity.ok().body(visitResponse);
    }
    @GetMapping
    public ResponseEntity<List<VisitResponse>> list(Pageable pageable) {
        return ResponseEntity.ok().body(visitService.findAllByPage(pageable));
    }
    @PostMapping
    public ResponseEntity<String> createVisitLog(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        visitService.createVisit(visitCreateRequest, userName);
        return ResponseEntity.ok().body(userName+"의 방문 기록이 등록 되었습니다.");
    }
}