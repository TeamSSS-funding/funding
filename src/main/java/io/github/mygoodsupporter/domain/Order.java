package io.github.mygoodsupporter.domain;

import lombok.Getter;

@Getter
public class Order {
    private Long id;
    private Long userId;
    private Long projectId;
    private Long rewardId;
    private int amount;
    private String paymentStatus;
}
