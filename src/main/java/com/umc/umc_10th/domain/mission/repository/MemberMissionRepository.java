package com.umc.umc_10th.domain.mission.repository;

import com.umc.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.umc.umc_10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);
    int countByMemberIdAndStatus(Long memberId, MissionStatus status);

    @Query("""
        SELECT mm FROM MemberMission mm
        JOIN FETCH mm.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.food f
        WHERE s.region.id = :regionId
        AND (mm.status = 'NOT_STARTED' OR mm.status = 'IN_PROGRESS')
        AND m.deadline > CURRENT_TIMESTAMP
    """)
    Page<MemberMission> findAvailableMissions(
            @Param("regionId") Long regionId,
            Pageable pageable
    );
}
