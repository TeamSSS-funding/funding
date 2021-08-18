package io.github.mygoodsupporter;

import lombok.Getter;

@Getter
public class OrderComplete {
    private Long id;
    private Long userId;
    private Long projectId;
    private Long rewardId;
    private int amount;
}
