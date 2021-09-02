package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.dto.ProjectDTO;
import io.github.mygoodsupporter.security.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RewardController {


    @GetMapping("/projects/{userId}/{projectId}/edit/rewards")
    public String getRewardsEdit(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId,
                                 @AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ProjectDTO dto){
        return "";
    }
}
