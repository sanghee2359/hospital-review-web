package com.hospital.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_NOT_FOUNDED(HttpStatus.NOT_FOUND,""),
    HOSPITAL_NOT_FOUNDED(HttpStatus.NOT_FOUND,""),
    DISEASE_NOT_FOUNDED(HttpStatus.NOT_FOUND, "");

    private HttpStatus httpStatus;
    private String message;
}
