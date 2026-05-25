package com.umc.umc_10th.domain.member.entity;

import com.umc.umc_10th.domain.member.enums.Gender;
import com.umc.umc_10th.domain.member.enums.SocialType;
import com.umc.umc_10th.domain.member.enums.RegionType;
import com.umc.umc_10th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "email", unique = true, length = 100, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "region", length = 20)
    private RegionType region;

    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Builder.Default
    @Column(name = "point", nullable = false)
    private Long point = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 20)
    private SocialType socialType;

    @Column(name = "social_uid", length = 255)
    private String socialUid;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
