package com.umc.umc_10th.domain.review.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    public record CreateReviewResponse(
            Long reviewId,
            Long storeId,
            Long memberId,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime createdAt
    ) {}

    // 리뷰 조회
    public record MyReviewItem(
            Long reviewId,
            Long storeId,
            String storeName,
            Double rating,
            String content,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime createdAt
    ){}

    // 커서 기반
    public record CursorInfo(
            String nextCursor,          // 복합 커서로 처리하기 위해 String 타입으로 지정
            boolean hasNext
    ){}

    public record MyReviewList(
            List<MyReviewItem> reviews,
            CursorInfo cursorInfo
    ){}

}

