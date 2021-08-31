package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Order;
import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.dto.ProjectDetails;
import io.github.mygoodsupporter.exception.NotYetImplementedException;
import io.github.mygoodsupporter.service.OrderService;
import io.github.mygoodsupporter.service.project.ProjectQueryService;
import io.github.mygoodsupporter.service.project.RewardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProjectQueryService projectQueryService;
    private final RewardService rewardService;

    public OrderController(OrderService orderService, ProjectQueryService projectQueryService, RewardService rewardService){
        this.orderService = orderService;
        this.projectQueryService = projectQueryService;
        this.rewardService = rewardService;
    }
    

    @GetMapping("/checkouts/{orderId}/payments/new")
    public String confirmPage(@PathVariable("orderId") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        ProjectDetails projectDetails = projectQueryService.getById(order.getProjectId());
        Reward reward = rewardService.getRewardById(order.getRewardId());

        model.addAttribute("order",order);
        model.addAttribute("projectDetails",projectDetails);
        model.addAttribute("reward",reward);
        return "payments/checkout";
    }

}
