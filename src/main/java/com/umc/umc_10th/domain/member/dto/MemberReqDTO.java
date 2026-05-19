package com.umc.umc_10th.domain.member.dto;

import com.umc.umc_10th.domain.member.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MemberReqDTO {

    // 회원 가입
    public record SignUp(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "닉네임은 필수입니다.")
            @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하입니다.")
            String nickname,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,                  // MALE or FEMALE

            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하입니다.")
            String password
    ) {}
}
