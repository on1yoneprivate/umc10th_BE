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

    // 별점순 조회
    // 별점이 같으면 reviewId 기준 정렬
    @Query("""
        SELECT r FROM Review r
        JOIN FETCH r.store s
        WHERE r.member.id = :memberId
        AND (
            :cursorRating IS NULL OR r.rating < :cursorRating OR (r.rating = :cursorRating AND r.id < :cursorId)
        )
        ORDER BY r.rating DESC, r.id DESC
    """)
    List<Review> findMyReviewsOrderByRating(
            @Param("memberId") Long memberId,
            @Param("cursorRating") Double cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
