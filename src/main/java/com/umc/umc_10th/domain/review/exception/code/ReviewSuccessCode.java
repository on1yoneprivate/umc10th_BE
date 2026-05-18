package com.umc.umc_10th.domain.review.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATE_SUCCESS(HttpStatus.OK, "REVIEW201_01", "리뷰가 성공적으로 작성되었습니다."),
    REVIEW_GET_SUCCESS(HttpStatus.OK, "REVIEW200-01", "리뷰 조회에 성공했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
