package io.github.mygoodsupporter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Order {
    private Long id;
    private Long userId;
    private Long projectId;
    private Long rewardId;
    private int amount;
    private OrderStatus orderStatus;

    @Builder
    public Order(Long userId, Long projectId, Long rewardId, int amount, OrderStatus orderStatus) {
        this.userId = userId;
        this.projectId = projectId;
        this.rewardId = rewardId;
        this.amount = amount;
        this.orderStatus = orderStatus;
    }
}
