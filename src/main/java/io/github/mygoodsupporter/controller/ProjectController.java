package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.dto.SupportProjectForm;
import io.github.mygoodsupporter.mapper.UserMapper;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    private final UserMapper userMapper;

    // 프로젝트 신청 화면 요청 메이커
    @RequestMapping(value="/projectRequestPage")
    public String projectRequestPage(@AuthenticationPrincipal UserDetails userDetails) {
        return "projects/projectRequest";
    }

    //프로젝트 신청페이지 메이커
    @RequestMapping(value="/projectRequest")
    public String projectRequest(@ModelAttribute Project pdto, @AuthenticationPrincipal UserDetails userDetails, Model model){
        //현재 로그인된 아이디 가져옴
        Project project = new Project(userDetails.getId(), pdto.getSubtitle());
        project.setUserId(userDetails.getId());

        pdto = projectService.projectRequest(pdto);
        model.addAttribute("pdto", pdto);
        return "redirect:/projects";
    }


    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<Project> projects = projectService.getProjects();

        model.addAttribute("projects", projects);
        return "projects/projectList";
    }

    @GetMapping("/projects/{projectId}")
    public String getProjectById(@PathVariable("projectId") Long projectId, Model model) {
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("supportProjectForm", new SupportProjectForm());
        return "projects/project";
    }

    @PostMapping("/projects/{projectId}/support")
    public String supportProject(@PathVariable("projectId") Long projectId, @AuthenticationPrincipal UserDetails userDetails,
                                 SupportProjectForm form, Model model) {
        String username = userDetails.getUsername();

        projectService.supportProject(username, projectId, form.getAmount());

        return "redirect:/projects/" + projectId;
    }
}
