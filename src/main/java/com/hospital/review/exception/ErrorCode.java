package com.hospital.review.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_NOT_FOUNDED(HttpStatus.NOT_FOUND,""),
    HOSPITAL_NOT_FOUNDED(HttpStatus.NOT_FOUND,""),
    DISEASE_NOT_FOUNDED(HttpStatus.NOT_FOUND, ""),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
    USERNAME_NOTFOUND(HttpStatus.NOT_FOUND,"" ),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"" );

    private HttpStatus httpStatus;
    private String message;
}
