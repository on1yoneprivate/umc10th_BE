package com.umc.umc_10th.domain.member.service;

import com.umc.umc_10th.domain.member.dto.MemberReqDTO;
import com.umc.umc_10th.domain.member.dto.MemberResDTO;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public final MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {
        return null;
    }
}
