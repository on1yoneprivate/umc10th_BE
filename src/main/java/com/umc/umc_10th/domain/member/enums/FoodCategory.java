package com.umc.umc_10th.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodCategory {

    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨"),
    SNACK("분식"),
    MEAT("고기/구이"),
    DOSIRAK("도시락"),
    NIGHT_MEAL("야식"),
    FAST_FOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN("아시안푸드");

    private final String description;
}