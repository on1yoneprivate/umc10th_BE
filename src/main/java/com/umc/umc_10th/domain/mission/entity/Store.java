package com.umc.umc_10th.domain.mission.entity;

import com.umc.umc_10th.domain.member.entity.Food;
import com.umc.umc_10th.domain.mission.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;            // 음식점명

    @Column(name= "address", nullable = false)
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private Status Status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
