package com.umc.umc_10th.global.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;      // 1시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;      // 7일
    private final Map<String, String> refreshTokenStorage = new HashMap<>();        // 리프레시 토큰 저장소

    // SECRET_KEY 초기화
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        if (secretKey == null || secretKey.isBlank()) {
            throw new RuntimeException("Secret Key is null or blank");
        }

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("Secret Key Loaded: " + secretKey.substring(0, 5) + "****");
    }

    // Access Token 생성
    public String generateAccessToken(Long memberId, String email) {
        return createToken(memberId, email, ACCESS_TOKEN_EXPIRE_TIME);
    }

    // Refresh Token 생성
    public String generateRefreshToken(Long memberId, String email) {
        String refreshToken = createToken(memberId, email, REFRESH_TOKEN_EXPIRE_TIME);
        refreshTokenStorage.put(email, refreshToken);

        return refreshToken;
    }

    // JWT에서 email 추출
    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    // JWT에서 memberId 추출
    public Long extractMemberId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    // JWT 유효성 검사
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    // 로그아웃 시 리프레시 토큰 무효화
    public void revokeRefreshToken(String email) {
        refreshTokenStorage.remove(email);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String createToken(Long memberId, String email, long expireTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expireTime);

        return Jwts.builder()
                .subject(email)
                .claim("memberId", memberId)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(secretKey)
                .compact();
    }
}
