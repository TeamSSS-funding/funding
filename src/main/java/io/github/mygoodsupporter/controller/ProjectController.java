package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.dto.ProjectDTO;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.CategoryService;
import io.github.mygoodsupporter.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final CategoryService categoryService;

    @GetMapping(value="/projects/new")
    public String projectRequestPage(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "projects/projectRequest";
    }

    @PostMapping(value="/projects/new")
    public String projectRequest(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ProjectDTO dto) {
        dto.setUserId(userDetails.getId());
        Long projectId = projectService.createProject(dto);
        String redirectUrl = "/projects/" + userDetails.getId() + "/" +  projectId + "/build";
        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = "/projects/{userId}/{projectId}/build")
    public String buildPage(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, @AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userId.equals(userDetails.getId())) {
            Project project = projectService.getProjectById(projectId);
            model.addAttribute("project", project);
            return "builder/projectBuilderPage";
        } else {
            return "error/404";
        }
    }

    @GetMapping(value = "/projects/{userId}/{projectId}/edit/basics")
    public String basicsPage(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, @AuthenticationPrincipal UserDetails userDetails, Model model){
        if(userId.equals(userDetails.getId())) {
            Project project = projectService.getProjectById(projectId);
            List<Category> categories = categoryService.getCategories();

            model.addAttribute("categories", categories);
            model.addAttribute("project", project);
            return "builder/editProjectPage";
        } else {
            return "error/404";
        }
    }

    @PostMapping(value = "/projects/{userId}/{projectId}/edit/basics")
    public String basics(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId,
                         @AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ProjectDTO dto) throws IOException {
        if(userId.equals(userDetails.getId())) {
        dto.setId(projectId);
        projectService.updateProject(dto);
        String redirectUrl = "/projects/" + userId + "/" + projectId + "/edit/basics";
        return "redirect:" + redirectUrl;
        } else {
            return "error/404";
        }
    }


    @GetMapping(value = "/projects/projectList")
    public String projectList(Model model){
        List<Project> projectList =  projectService.getProjectsByAll();
        model.addAttribute("projectList", projectList);
        return "projects/projectList";
    }

    @GetMapping("/projects/created")
    public String getMyProject(@AuthenticationPrincipal UserDetails userDetails, Model model){
        List<Project> projectList = projectService.getProjectsByUserId(userDetails.getId());
        model.addAttribute("projectList",projectList);
        return "projects/projectCreated";
    }

    @GetMapping(value = "/projects/projectList/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws IOException {
        projectService.deleteProject(id);
        return "redirect:/projects/projectList";
    }


} 
