package com.hospital.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalReviewAppException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;
}
