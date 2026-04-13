package com.umc.umc_10th.domain.member.controller;

import com.umc.umc_10th.domain.member.dto.MemberReqDTO;
import com.umc.umc_10th.domain.member.dto.MemberResDTO;
import com.umc.umc_10th.domain.member.exception.code.MemberSuccessCode;
import com.umc.umc_10th.domain.member.service.MemberService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUp> signup
            (@RequestBody MemberReqDTO.SignUp request) {

        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.signUp(request));
    }
}
