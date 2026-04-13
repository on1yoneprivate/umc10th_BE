package com.umc.umc_10th.domain.mission.service;

import com.umc.umc_10th.domain.mission.dto.MissionResDTO;
import com.umc.umc_10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    // 추후 미션 중 / 미션 완료 로직 개발
    public MissionResDTO.MissionList getInProgressMissions(int page, int size) {
        return null;
    }

    public MissionResDTO.MissionList getCompletedMissions(int page, int size){
        return null;
    }
}
