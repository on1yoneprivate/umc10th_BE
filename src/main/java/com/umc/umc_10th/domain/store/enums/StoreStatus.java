package com.umc.umc_10th.domain.store.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StoreStatus {
    OPEN("영업 중"),
    CLOSED("영업 종료");

    private final String description;
}
