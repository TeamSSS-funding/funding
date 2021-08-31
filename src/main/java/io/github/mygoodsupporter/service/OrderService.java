package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Order;
import io.github.mygoodsupporter.domain.OrderStatus;
import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.mapper.OrderMapper;
import io.github.mygoodsupporter.service.project.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final RewardService rewardService;

    @Transactional
    public Long createOrder(Long userId, Long rewardId) {
        Reward reward = rewardService.getRewardById(rewardId);
        Long projectId = reward.getProjectId();

        Order order = Order.builder()
                .userId(userId)
                .projectId(projectId)
                .rewardId(rewardId)
                .amount(reward.getAmount())
                .orderStatus(OrderStatus.PENDING)
                .build();

        orderMapper.insertOrder(order);
        return order.getId();
    }


    public Order getOrderById(Long orderId) {
        return orderMapper.getOrderById(orderId);
    }
}
