package com.umc.umc_10th.domain.mission.repository;

import com.umc.umc_10th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
