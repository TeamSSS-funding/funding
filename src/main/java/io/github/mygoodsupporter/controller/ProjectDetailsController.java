package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.dto.ProjectDetails;
import io.github.mygoodsupporter.service.project.ProjectQueryService;
import io.github.mygoodsupporter.service.project.RewardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectDetailsController {

    private final ProjectQueryService projectQueryService;
    private final RewardService rewardService;

    public ProjectDetailsController(ProjectQueryService projectQueryService,RewardService rewardService){
        this.projectQueryService = projectQueryService;
        this.rewardService = rewardService;
    }

    @GetMapping("/projects/{userId}/{projectId}")
    public String getProjectDetailsPage(@PathVariable("projectId") Long id, Model model){
        ProjectDetails projectDetails = projectQueryService.getById(id);
        model.addAttribute("projectDetails", projectDetails);
        return "projects/projectView";
    }

    @GetMapping("/projects/{userId}/{projectId}/support/new")
    public String getSupportPage(@PathVariable("projectId") Long id, Model model){
        List<Reward> rewards = rewardService.getRewardsByProjectId(id);
        model.addAttribute("rewards",rewards);
        return "projects/projectSupport";
    }

}
