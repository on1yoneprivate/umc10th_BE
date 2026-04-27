package com.umc.umc_10th.global.apiPayLoad.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E-400-01", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "E-401-01", "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "E-403-01", "접근이 금지되었습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E-500-01", "서버 내부에 오류가 발생했습니다."),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
