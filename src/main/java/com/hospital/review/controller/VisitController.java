package com.hospital.review.controller;

import com.hospital.review.domain.dto.VisitCreateRequest;
import com.hospital.review.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    private final VisitService visitService;
    @PostMapping("") // 전체조회
    public ResponseEntity<String> createVisit(@RequestBody VisitCreateRequest dto
            , Authentication authentication) {
        String userName = authentication.name();
        String code = authentication.name();
        visitService.createVisit(dto, userName,code);
        return ResponseEntity.ok().body("방문 기록이 등록 되었습니다.");
    }

}
