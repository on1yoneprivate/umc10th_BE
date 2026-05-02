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
import com.umc.umc_10th.global.apiPayLoad.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewResponse createReview(
            Long storeId, Long memberId, ReviewReqDTO.CreateReviewRequest request){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ReviewErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ReviewErrorCode.MEMBER_NOT_FOUND));

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

    // 내가 작성한 리뷰 조회 - reviewId순 조회
    public ReviewResDTO.MyReviewList getMyReviewsOrderById(
            Long memberId, Long cursor, int size) {

        Pageable pageable = PageRequest.of(0, size+1);

        List<Review> reviews = reviewRepository.findMyReviewsOrderById(
                memberId, cursor, pageable
        );

        return toMyReviewList(reviews, size);
    }

    // 내가 작성한 리뷰 조회 - 별점순 조회
    public ReviewResDTO.MyReviewList getMyReviewsOrderByRating(
            Long memberId, Double cursorRating, Long cursorId, int size) {

        Pageable pageable = PageRequest.of(0, size+1);

        List<Review> reviews = reviewRepository.findMyReviewsOrderByRating(
                memberId, cursorRating, cursorId, pageable);

        return toMyReviewList(reviews, size);
    }

    private ReviewResDTO.MyReviewList toMyReviewList(List<Review> reviews, int size) {
        boolean hasNext = reviews.size() > size;

        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        List<ReviewResDTO.MyReviewItem> reviewItems = content.stream()
                .map(review -> new ReviewResDTO.MyReviewItem(
                        review.getId(),
                        review.getStore().getId(),
                        review.getStore().getName(),
                        review.getRating(),
                        review.getContent(),
                        review.getCreatedAt()
                ))
                .toList();

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return new ReviewResDTO.MyReviewList(
                reviewItems,
                new ReviewResDTO.CursorInfo(nextCursor, hasNext)
        );
    }
}
