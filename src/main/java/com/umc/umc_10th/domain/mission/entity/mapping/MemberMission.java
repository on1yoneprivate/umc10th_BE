package com.umc.umc_10th.domain.mission.entity.mapping;

import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.mission.entity.Mission;
import com.umc.umc_10th.domain.mission.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MissionStatus status;

    @Column(name = "challenged_at", nullable = false)
    private LocalDateTime challengedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}