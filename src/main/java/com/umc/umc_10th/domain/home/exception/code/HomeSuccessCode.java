package com.umc.umc_10th.domain.home.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeSuccessCode implements BaseSuccessCode {

    HOME_SUMMARY_SUCCESS(HttpStatus.OK, "HOME200_01", "성공적으로 조회되었습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
