package com.umc.umc_10th.domain.review.service;

import com.umc.umc_10th.domain.review.dto.ReviewReqDTO;
import com.umc.umc_10th.domain.review.dto.ReviewResDTO;
import com.umc.umc_10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public final ReviewResDTO.CreateReview createReview(ReviewReqDTO.CreateReview request){
        return null;
    }
}
