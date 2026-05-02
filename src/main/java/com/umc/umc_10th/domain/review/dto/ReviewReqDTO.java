package com.umc.umc_10th.domain.review.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewRequest(
        @NotNull(message = "평점은 필수 입력입니다.")
        @DecimalMin(value = "0.0", message = "평점은 0점 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "평점은 5점 이하여야 합니다.")
        Double rating,

        @NotBlank(message = "리뷰 내용을 작성해 주세요.")
        String content
    ) {}

}
