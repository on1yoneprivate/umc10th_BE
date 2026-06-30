package com.umc.umc_10th.domain.member.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "E-401-01", "비밀번호가 일치하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "E-404-01", "해당 회원을 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "E-409-01", "이미 사용 중인 이메일입니다."),
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "E-409-02", "이미 사용 중인 닉네임입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
