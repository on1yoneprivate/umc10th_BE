package com.umc.umc_10th.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermType {
    AGE("만 14세 이상"),
    SERVICE("서비스 이용 약관"),
    PRIVACY("개인 정보 처리 방침"),
    LOCATION("위치 정보 제공"),
    MARKETING("마케팅 수신 동의");

    private final String description;
}
