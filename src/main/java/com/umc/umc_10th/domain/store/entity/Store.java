package com.umc.umc_10th.domain.store.entity;

import com.umc.umc_10th.domain.member.entity.Food;
import com.umc.umc_10th.domain.store.enums.StoreStatus;
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
    @Column(name = "store_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;            // 음식점명

    @Column(name = "manager_number", nullable = false)
    private Long managerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "region_id", nullable = false)
    private Region region;

    @Column(name = "detail_address")
    private String detailAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private StoreStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
