package com.umc.umc_10th.domain.mission.controller;

import com.umc.umc_10th.domain.mission.dto.MissionReqDTO;
import com.umc.umc_10th.domain.mission.dto.MissionResDTO;
import com.umc.umc_10th.domain.mission.exception.code.MissionSuccessCode;
import com.umc.umc_10th.domain.mission.service.MissionService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/in-progress")
    public ApiResponse<MissionResDTO.MissionList> getInProgressMissions(
            @RequestBody MissionReqDTO.InProgressMissionRequest request,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_IN_PROGRESS_LIST_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getInProgressMissions(request.memberId(), page, size));
    }

    @GetMapping("/completed")
    public ApiResponse<MissionResDTO.MissionList> getCompletedMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETED_LIST_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getCompletedMissions(memberId, page, size));
    }

    @PutMapping("/{userMissionId}/success")
    public ApiResponse<MissionResDTO.MissionSuccess> completeMission(
            @PathVariable @Min(1) Long userMissionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_SUCCESS_UPDATE;

        return ApiResponse.onSuccess(code, missionService.completeMission(userMissionId)
        );
    }
}
