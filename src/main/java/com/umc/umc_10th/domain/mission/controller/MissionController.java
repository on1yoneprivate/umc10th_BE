package com.umc.umc_10th.domain.mission.controller;

import com.umc.umc_10th.domain.mission.dto.MissionResDTO;
import com.umc.umc_10th.domain.mission.exception.code.MissionSuccessCode;
import com.umc.umc_10th.domain.mission.service.MissionService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/in-progress")
    public ApiResponse<MissionResDTO.MissionList> getInProgressMissions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_IN_PROGRESS_LIST_SUCCESS; // 성공 코드 연결
        return ApiResponse.onSuccess(code, missionService.getInProgressMissions(page, size));
    }

    @GetMapping("/completed")
    public ApiResponse<MissionResDTO.MissionList> getCompletedMissions(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETED_LIST_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getCompletedMissions(page, size));
    }
}
