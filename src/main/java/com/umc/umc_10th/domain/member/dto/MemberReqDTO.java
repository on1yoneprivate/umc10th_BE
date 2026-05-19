package com.umc.umc_10th.domain.member.dto;

import com.umc.umc_10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

    // 회원 가입
    public record SignUp(
            String email,
            String nickname,
            Gender gender,                  // MALE or FEMALE
            LocalDate birth,
            String password
    ) {}
}
