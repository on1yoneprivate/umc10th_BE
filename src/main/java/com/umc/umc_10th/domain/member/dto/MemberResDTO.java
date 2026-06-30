package com.umc.umc_10th.domain.member.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class MemberResDTO {

    // 회원가입
    public record SignUp(
            Long memberId,
            String nickname,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime createdAt
    ){}

    // 로그인
    public record Login(
            Long memberId,
            String email,
            String accessToken,
            String refreshToken
    ) {}

    // 마이페이지
    public record MyPage(
            Long memberId,
            String nickname,
            String email,
            String phone,
            Boolean isPhoneVerified,
            Long point,
            String profileUrl
    ) {}
}
