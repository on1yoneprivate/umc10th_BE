package com.umc.umc_10th.domain.member.entity;

import com.umc.umc_10th.domain.member.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private TermType name;

    // 필수 동의
    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

}
