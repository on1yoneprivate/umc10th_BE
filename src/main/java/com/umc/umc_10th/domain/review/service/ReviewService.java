package com.umc.umc_10th.domain.review.service;

import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import com.umc.umc_10th.domain.review.dto.ReviewReqDTO;
import com.umc.umc_10th.domain.review.dto.ReviewResDTO;
import com.umc.umc_10th.domain.review.entity.Review;
import com.umc.umc_10th.domain.review.exception.code.ReviewErrorCode;
import com.umc.umc_10th.domain.review.repository.ReviewRepository;
import com.umc.umc_10th.domain.store.entity.Store;
import com.umc.umc_10th.domain.store.repository.StoreRepository;
import com.umc.umc_10th.global.apiPayLoad.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public final ReviewResDTO.CreateReviewResponse createReview(
            Long storeId, Long memberId, ReviewReqDTO.CreateReview request){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(ReviewErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(ReviewErrorCode.MEMBER_NOT_FOUND));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .rating(request.rating())
                .content(request.content())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewResDTO.CreateReviewResponse(
                savedReview.getId(),
                savedReview.getStore().getId(),
                savedReview.getMember().getId(),
                savedReview.getCreatedAt()
        );
    }
}
