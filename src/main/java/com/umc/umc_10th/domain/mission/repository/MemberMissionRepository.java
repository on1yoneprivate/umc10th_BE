package com.umc.umc_10th.domain.mission.repository;

import com.umc.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.umc.umc_10th.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    int countByMemberIdAndStatus(Long memberId, MissionStatus status);

    // 해당 지역에서 진행 가능한 미션 목록
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

    // 사용자가 현재 진행 중인 미션 목록
    @Query(
        value = """
            SELECT mm FROM MemberMission mm
            JOIN FETCH mm.mission m
            JOIN FETCH m.store s
            WHERE mm.member.id = :memberId
            AND mm.status = :status
            ORDER BY mm.challengedAt DESC  
        """,
        countQuery = """
            SELECT COUNT(mm) FROM MemberMission mm
            WHERE mm.member.id = :memberId
            AND mm.status = :status
        """
    )
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}
