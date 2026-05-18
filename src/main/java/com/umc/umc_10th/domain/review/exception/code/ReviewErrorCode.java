package com.umc.umc_10th.domain.review.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    INVALID_CURSOR(HttpStatus.BAD_REQUEST, "E-400-01", "올바르지 않은 커서 형식입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404-01", "해당 가게를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404-02", "해당 회원을 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404-03", "해당 지역을 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
