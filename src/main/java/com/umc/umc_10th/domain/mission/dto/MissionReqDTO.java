package com.umc.umc_10th.domain.mission.dto;

public class MissionReqDTO {

    public record InProgressMissionRequest (
            Long memberId
    ) {}
}
