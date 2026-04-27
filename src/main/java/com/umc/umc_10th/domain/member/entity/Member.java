package com.umc.umc_10th.domain.member.entity;

import com.umc.umc_10th.domain.member.enums.Gender;
import com.umc.umc_10th.domain.member.enums.SocialType;
import com.umc.umc_10th.domain.member.enums.Address;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "email", unique = true, length = 100, nullable = true)
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

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "address", length = 20)
    private Address address;

    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Column(name = "point", nullable = false)
    private Long point = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 20, nullable = true)
    private SocialType socialType;
}
