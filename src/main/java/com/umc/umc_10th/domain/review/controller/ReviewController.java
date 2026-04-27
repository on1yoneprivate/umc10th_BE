package com.umc.umc_10th.domain.review.controller;

import com.umc.umc_10th.domain.review.dto.ReviewReqDTO;
import com.umc.umc_10th.domain.review.dto.ReviewResDTO;
import com.umc.umc_10th.domain.review.exception.code.ReviewSuccessCode;
import com.umc.umc_10th.domain.review.service.ReviewService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request) {

        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, memberId,request));

    }
}
