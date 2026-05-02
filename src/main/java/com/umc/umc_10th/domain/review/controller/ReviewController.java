package com.umc.umc_10th.domain.review.controller;

import com.umc.umc_10th.domain.review.dto.ReviewReqDTO;
import com.umc.umc_10th.domain.review.dto.ReviewResDTO;
import com.umc.umc_10th.domain.review.exception.code.ReviewSuccessCode;
import com.umc.umc_10th.domain.review.service.ReviewService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResponse> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewRequest request) {

        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE_SUCCESS;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, memberId,request));

    }

    // 작성한 리뷰 조회 - reviewId순 조회
    @GetMapping("/my/id")
    public ApiResponse<ReviewResDTO.MyReviewList> getMyReviewsOrderById(
            @RequestParam Long memberId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") @Min(1) int size
    ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET_SUCCESS;
        return ApiResponse.onSuccess(code, reviewService.getMyReviewsOrderById(memberId, cursor, size));
    }
}
