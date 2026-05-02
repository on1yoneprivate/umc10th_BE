package com.umc.umc_10th.domain.mission.repository;

import com.umc.umc_10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findByStoreRegionId(Long regionId, Pageable pageable);
}
