package com.umc.umc_10th.global.security.jwt;

import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 요청 헤더에서 JWT 추출
        String token = resolveToken(request);

        if (token != null && jwtUtil.validateToken(token)) {

            Long memberId = jwtUtil.extractMemberId(token);
            Member member = memberRepository.findById(memberId).orElse(null);

            // Spring Security 인증 객체 생성
            if (member != null) {
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        member.getId()              // 인증 사용자
                        , null, List.of()
                );

                // SecurityContext에 인증 정보 저장
                // 이후 Controller에서 Authentication으로 접근 가능
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request,response);
    }

    // Authorization 헤더에서 Bearer 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        // Bearer 토큰 형식 체크
        if (authorization != null && authorization.startsWith("Bearer ")) {

            // "Bearer " 제거 후 실제 JWT 반환
            return authorization.substring(7);
        }

        return null;
    }
}
