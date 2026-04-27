package com.umc.umc_10th.domain.home.service;

import com.umc.umc_10th.domain.home.dto.HomeResDTO;
import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import com.umc.umc_10th.domain.mission.entity.Mission;
import com.umc.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.umc.umc_10th.domain.mission.enums.MissionStatus;
import com.umc.umc_10th.domain.mission.repository.MemberMissionRepository;
import com.umc.umc_10th.domain.mission.repository.MissionRepository;
import com.umc.umc_10th.domain.review.exception.code.ReviewErrorCode;
import com.umc.umc_10th.domain.store.entity.Region;
import com.umc.umc_10th.domain.store.repository.RegionRepository;
import com.umc.umc_10th.global.apiPayLoad.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public HomeResDTO.Summary getHomeSummary(Long memberId, Long regionId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ProjectException(ReviewErrorCode.REGION_NOT_FOUND));

        int completedCount = memberMissionRepository.countByMemberIdAndStatus(
                memberId,
                MissionStatus.DONE
        );

        int goalCount = 10;
        int bonusPoint = 1000;
        int progressRate = (int) ((completedCount / (double) goalCount) * 100);

        return new HomeResDTO.Summary(
                region.getId(),
                region.getName(),
                member.getPoint().intValue(),
                completedCount,
                goalCount,
                bonusPoint,
                progressRate
        );
    }

    public HomeResDTO.HomeMissionList getHomeMissions(Long regionId, int page, int size) {

        Page<MemberMission> memberMissions =
                memberMissionRepository.findAvailableMissions(
                        regionId,
                        PageRequest.of(page, size)
                );

        return new HomeResDTO.HomeMissionList(
                memberMissions.getContent().stream()
                        .map(mm -> new HomeResDTO.HomeMissionItem(
                                mm.getMission().getId(),
                                mm.getMission().getStore().getId(),
                                mm.getMission().getStore().getName(),
                                mm.getMission().getStore().getFood().getCategory().name(),
                                mm.getMission().getCondition(),
                                mm.getMission().getPoint(),
                                mm.getMission().getDeadline()
                        ))
                        .toList(),
                new HomeResDTO.PageInfo(
                        memberMissions.getNumber(),
                        memberMissions.getSize(),
                        memberMissions.getTotalElements(),
                        memberMissions.getTotalPages(),
                        memberMissions.hasNext()
                )
        );
    }
}
