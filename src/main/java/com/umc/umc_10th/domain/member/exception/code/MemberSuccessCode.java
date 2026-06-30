package com.umc.umc_10th.domain.member.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    SIGNUP_SUCCESS(HttpStatus.CREATED, "M-201-01", "회원가입이 성공적으로 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "M-200-01", "로그인이 성공적으로 완료되었습니다."),
    MYPAGE_SUCCESS(HttpStatus.OK, "M-200-02", "마이페이지가 성공적으로 조회되었습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
