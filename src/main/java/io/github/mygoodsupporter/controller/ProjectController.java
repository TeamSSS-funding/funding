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
        log.debug(userDetails.getUsername());
        log.debug(userDetails.getEmail());
        log.debug(userDetails.getUsername());
        log.debug(userDetails.getAuthorities().toString());
        return "projects/projectRequest";
    }

    //프로젝트 신청페이지 메이커
    @RequestMapping(value="/projectRequest")
    public String projectRequest(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Project pdto, Model model){
        //현재 로그인된 아이디 가져옴
        Project project = new Project();
        project.setUserId(userDetails.getId());

        pdto = projectService.projectRequest(pdto);
        model.addAttribute("pdto", pdto);
        return "projects/projectList";
    }


    @GetMapping("/projects")
    public String getProjects(Model model) {
        List<Project> projects = projectService.getProjects();

        model.addAttribute("projects", projects);
        return "projects/projectList";
    }

    @GetMapping("/projects/{slug}")
    public String getProjectBySlugName(@PathVariable("slug") String slug, Model model) {
        Project project = projectService.getProjectBySlug(slug);
        model.addAttribute("project", project);
        model.addAttribute("supportProjectForm", new SupportProjectForm());
        return "projects/project";
    }

    @PostMapping("/projects/{slug}/support")
    public String supportProject(@PathVariable("slug") String slug, @AuthenticationPrincipal UserDetails userDetails,
                                 SupportProjectForm form, Model model) {
        String memberId =  userDetails.getUsername();

        projectService.supportProject(memberId, slug, form.getAmount());

        return "redirect:/projects/" + slug;
    }
}
