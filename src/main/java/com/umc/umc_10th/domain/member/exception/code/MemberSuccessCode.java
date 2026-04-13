package com.umc.umc_10th.domain.member.exception.code;

import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_01", "회원가입이 성공적으로 완료되었습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
