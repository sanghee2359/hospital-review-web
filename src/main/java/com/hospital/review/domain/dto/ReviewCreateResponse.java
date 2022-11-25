package com.hospital.review.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewCreateResponse {
    private String userName;
    private String title;
    private String content;
    private String message; // 요청이 성공했는지 나타내는 메세지
}
