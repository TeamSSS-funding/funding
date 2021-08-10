package io.github.mygoodsupporter.controller.project;

import io.github.mygoodsupporter.mapper.ProjectMapper;
import io.github.mygoodsupporter.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BuilderController {

    private final ProjectMapper projectMapper;
    private final RewardRepository rewardRepository;

    @GetMapping("/projects/start")
    public String startProjectPage(Model model) {
        return "redirect:/";
    }

    @PostMapping("/projects/start")
    public String startProject() {
        return "redirect:/";
    }

    @GetMapping("/projects/{userId}/{projectId}")
    public String getProjectsByUserId() {
        return "redirect:/";
    }

    @GetMapping("/projects/{userId}/{projectId}/build")
    public String getBuilderPage() {
        return "redirect:/";
    }

    @GetMapping("/projects/{userId}/{projectId}/edit/preview")
    public String getPreviewPage() {
        return "redirect:/";
    }

    @GetMapping("/projects/{userId}/{projectId}/edit/basics")
    public String getProjectBasicInformationPage() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/basics")
    public String updateProjectBasicInformation() {
        return "redirect:/";
    }

    @GetMapping("/projects/{userId}/{projectId}/edit/rewards")
    public String getRewardsPage() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/rewards")
    public String createReward() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/rewards/{rewardId}/edit")
    public String updateReward() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/rewards/{rewardId}/delete")
    public String deleteReward() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/items")
    public String createItem() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/rewards/{itemId}/edit")
    public String editItem() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/rewards/{itemId}/delete")
    public String deleteItem() {
        return "redirect:/";
    }


    @GetMapping("/projects/{userId}/{projectId}/edit/payment")
    public String getPaymentPage() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/edit/payment")
    public String updateBankAccount() {
        return "redirect:/";
    }

    @PostMapping("/projects/{userId}/{projectId}/delete")
    public String deleteProjectById(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId) {

        return "redirect:/";
    }

}
