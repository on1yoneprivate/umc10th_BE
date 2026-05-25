package com.umc.umc_10th.domain.member.controller;

import com.umc.umc_10th.domain.member.dto.MemberReqDTO;
import com.umc.umc_10th.domain.member.dto.MemberResDTO;
import com.umc.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.umc.umc_10th.domain.member.service.MemberService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUp> signup
            (@Valid @RequestBody MemberReqDTO.SignUp request) {

        BaseSuccessCode code = MemberSuccessCode.SIGNUP_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.signUp(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login
            (@Valid @RequestBody MemberReqDTO.Login request) {

        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, memberService.login(request));
    }

    @GetMapping("/mypage")
    public ApiResponse<MemberResDTO.MyPage> getMyPage(@RequestParam Long memberId) {

        return ApiResponse.onSuccess(MemberSuccessCode.MYPAGE_SUCCESS, memberService.getMyPage(memberId));
    }
}
