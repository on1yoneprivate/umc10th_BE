package com.umc.umc_10th.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.umc_10th.global.apiPayLoad.ApiResponse;
import com.umc.umc_10th.global.apiPayLoad.code.GeneralErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// 인증 실패
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");

        ApiResponse<Object> apiResponse = ApiResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED,
                null
        );

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
