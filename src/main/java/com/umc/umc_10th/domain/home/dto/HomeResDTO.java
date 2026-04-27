package com.umc.umc_10th.domain.home.dto;

public class HomeResDTO {

    public record Summary(
            Long regionId,
            String regionName,
            Integer point,
            Integer completedCount,
            Integer goalCount,
            Integer bonusPoint,
            Integer progressRate
    ) {}

}
