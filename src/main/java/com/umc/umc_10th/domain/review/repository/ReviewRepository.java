package com.umc.umc_10th.domain.review.repository;

import com.umc.umc_10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID순 조회
    @Query("""
        SELECT r FROM Review r
        JOIN FETCH r.store s
        WHERE r.member.id = :memberId
        AND (:cursor IS NULL or r.id < :cursor)
        ORDER BY r.id DESC
    """)
    List<Review> findMyReviewsOrderById(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
