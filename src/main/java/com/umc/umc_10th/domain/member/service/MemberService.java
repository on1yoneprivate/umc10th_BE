package com.umc.umc_10th.domain.member.service;

import com.umc.umc_10th.domain.member.dto.MemberReqDTO;
import com.umc.umc_10th.domain.member.dto.MemberResDTO;
import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import com.umc.umc_10th.global.apiPayLoad.exception.CustomException;
import com.umc.umc_10th.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp request) {

        if (memberRepository.existsByEmail(request.email())) {
            throw new CustomException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
        }

        if (memberRepository.existsByNickname(request.nickname())) {
            throw new CustomException(MemberErrorCode.NICKNAME_ALREADY_EXISTS);
        }

        Member member = Member.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .gender(request.gender())
                .birth(request.birth())
                .build();

        Member savedMember = memberRepository.save(member);

        return new MemberResDTO.SignUp(
                savedMember.getId(),
                savedMember.getNickname(),
                savedMember.getCreatedAt()
        );
    }

    public MemberResDTO.Login login(MemberReqDTO.Login request) {

        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(()-> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new CustomException(MemberErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtUtil.generateAccessToken(member.getId(), member.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(member.getId(), member.getEmail());

        return new MemberResDTO.Login(
                member.getId(),
                member.getEmail(),
                accessToken,
                refreshToken
        );
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
