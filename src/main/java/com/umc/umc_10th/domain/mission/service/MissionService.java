package com.umc.umc_10th.domain.mission.service;

import com.umc.umc_10th.domain.mission.dto.MissionResDTO;
import com.umc.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.umc.umc_10th.domain.mission.enums.MissionStatus;
import com.umc.umc_10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionList getInProgressMissions(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<MemberMission> memberMissions =
                memberMissionRepository.findByMemberIdAndStatus(
                        memberId,
                        MissionStatus.IN_PROGRESS,
                        pageable
                );

        return toMissionList(memberMissions);
    }

    public MissionResDTO.MissionList getCompletedMissions(Long memberId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<MemberMission> memberMissions =
                memberMissionRepository.findByMemberIdAndStatus(
                        memberId,
                        MissionStatus.DONE,
                        pageable
                );

        return toMissionList(memberMissions);
    }

    public MissionResDTO.MissionSuccess completeMission(Long userMissionId) {
        return null;
    }

    private MissionResDTO.MissionList toMissionList(Page<MemberMission> memberMissions) {

        List<MissionResDTO.MissionItem> missions = memberMissions.getContent().stream()
                .map(memberMission -> new MissionResDTO.MissionItem(
                        memberMission.getId(),
                        memberMission.getMission().getId(),
                        memberMission.getMission().getStore().getName(),
                        memberMission.getMission().getTitle(),
                        memberMission.getMission().getContent(),
                        memberMission.getMission().getCondition(),
                        memberMission.getMission().getPoint(),
                        memberMission.getStatus().name(),
                        memberMission.getChallengedAt(),
                        memberMission.getCompletedAt()
                ))
                .toList();

        MissionResDTO.PageInfo pageInfo = new MissionResDTO.PageInfo(
                memberMissions.getNumber(),
                memberMissions.getSize(),
                memberMissions.getTotalElements(),
                memberMissions.getTotalPages(),
                memberMissions.hasNext()
        );

        return new MissionResDTO.MissionList(missions, pageInfo);
    }
}
