package com.umc.umc_10th.domain.review.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.umc.umc_10th.domain.member.entity.Member;
import com.umc.umc_10th.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 가게 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 유저 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

}
