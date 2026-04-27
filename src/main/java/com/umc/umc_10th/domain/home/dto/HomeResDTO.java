package com.umc.umc_10th.domain.home.dto;

import java.time.LocalDateTime;
import java.util.List;

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

    public record HomeMissionList(
            List<HomeMissionItem> missions,
            PageInfo pageInfo
    ) {}

    public record HomeMissionItem(
            Long missionId,
            Long storeId,
            String storeName,
            String foodCategory,
            Long missionCondition,
            Long rewardPoint,
            LocalDateTime deadline
    ) {}

    public record PageInfo(
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean hasNext
    ) {}

}
