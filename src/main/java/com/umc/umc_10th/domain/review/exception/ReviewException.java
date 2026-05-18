package com.umc.umc_10th.domain.review.exception;

import com.umc.umc_10th.domain.review.exception.code.ReviewErrorCode;
import lombok.Getter;

@Getter
public class ReviewException extends RuntimeException {

    private final ReviewErrorCode errorCode;

    public ReviewException(ReviewErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
