package com.hospital.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VisitCreateRequest {
    private Long hospitalId;
    private String disease;
    private float amount;
}
