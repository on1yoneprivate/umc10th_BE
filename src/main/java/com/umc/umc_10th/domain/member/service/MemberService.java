package com.umc.umc_10th.domain.member.service;

import com.umc.umc_10th.domain.member.dto.MemberReqDTO;
import com.umc.umc_10th.domain.member.dto.MemberResDTO;
import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import com.umc.umc_10th.global.apiPayLoad.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {
        return null;
    }

    public MemberResDTO.MyPage getMyPage(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        return new MemberResDTO.MyPage(
                member.getId(),
                member.getNickname(),
                member.getEmail(),
                member.getPhone(),
                member.getPhone() != null,
                member.getPoint(),
                null
        );
    }
}
