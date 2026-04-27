package com.umc.umc_10th.domain.home.controller;

import com.umc.umc_10th.domain.home.dto.HomeResDTO;
import com.umc.umc_10th.domain.home.exception.code.HomeSuccessCode;
import com.umc.umc_10th.domain.home.service.HomeService;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/summary")
    public ApiResponse<HomeResDTO.Summary> getHomeSummary() {

        BaseSuccessCode code = HomeSuccessCode.HOME_SUMMARY_SUCCESS;

        return ApiResponse.onSuccess(code, homeService.getHomeSummary()
        );
    }
}
