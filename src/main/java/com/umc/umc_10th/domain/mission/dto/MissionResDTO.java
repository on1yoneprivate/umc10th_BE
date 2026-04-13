package com.umc.umc_10th.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    public record MissionItem(
            Long userMissionId,
            Long missionId,
            String storeName,
            String title,
            String description,
            String missionType,
            Integer targetAmount,
            Integer rewardPoint,
            String status,
            LocalDateTime challengedAt,
            LocalDateTime completedAt
    ) {
    }

    public record PageInfo(
            int page,
            int size,
            long totalElements,
            int totalPages,
            boolean hasNext
    ) {}

    public record MissionList(
            List<MissionItem> missions,
            PageInfo pageInfo
    ) {}
}
