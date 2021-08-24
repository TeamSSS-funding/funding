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

    //프로젝트 신청 1단계 화면 요청
    @GetMapping(value="/projects/new")
    public String projectRequestPage(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "projects/projectRequest";
    }

    //프로젝트 신청 1단계처리
    @PostMapping(value="/projects/new")
    public String projectRequest(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute ProjectDTO dto) {
        dto.setUserId(userDetails.getId());
        Long projectId = projectService.createProject(dto);
        String redirectUrl = "/projects/" + userDetails.getId() + "/" +  projectId + "/build";
        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = "/projects/{userId}/{projectId}/build")
    public String buildPage(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, Model model){
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project",project);
        return "builder/projectBuilderPage";
    }

    //프로젝트 신청 2단계 화면 요청
    @GetMapping(value = "/projects/{userId}/{projectId}/edit/basics")
    public String basicsPage(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, Model model){
        Project project = projectService.getProjectById(projectId);
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("project",project);
        return "builder/editProjectPage";
    }

    //프로젝트 신청 2단계 처리
    @PostMapping(value = "/projects/{userId}/{projectId}/edit/basics")
    public String basics(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, @ModelAttribute ProjectDTO dto) throws IOException {
        dto.setId(projectId);
        projectService.updateProject(dto);
        String redirectUrl = "/projects/" + userId + "/" + projectId + "/edit/basics";
        return "redirect:" + redirectUrl;
    }


    @GetMapping(value = "/projects/projectList")
    public String projectList(@AuthenticationPrincipal UserDetails userDetails, Model model){
        List<Project> projectList =  projectService.getProjectsByUserId(userDetails.getId());
        model.addAttribute("projectList", projectList);
        return "projects/projectList";
    }

//    @GetMapping(value = "/projects/{userId}/{projectId}/edit/basics")
//    public String updatePage(@PathVariable("userId") Long userId, @PathVariable("projectId") Long projectId, Model model){
//        Project project = projectService.getProjectById(projectId);
//        model.addAttribute("project",project);
//        return "builder/editProjectPage";
//    }

//    @PostMapping(value = "/projects/projectList/update/{id}")
//    public String update(@ModelAttribute Project updateProject,@RequestParam("contentsImage") MultipartFile file, @PathVariable("id") Long id) throws IOException {
//        Project project = projectService.getProjectById(id);
//        if(file.getSize() == 0){
//            updateProject.setContentsImageUrl(project.getContentsImageUrl());
//        } else {
//            String[] fileName = project.getContentsImageUrl().split("/");
//            s3Service.delete(fileName[3]);
//
//            String imgPath = s3Service.upload(file);
//            updateProject.setContentsImageUrl(imgPath);
//        }
//        projectService.updateProces(updateProject);
//
//
//        return "redirect:/projects/projectList";
//    }

//    @GetMapping(value = "/projects/projectList/delete/{id}")
//    public String delete(@PathVariable("id") Long id) throws IOException {
//        Project project = projectService.getProjectById(id);
//        if(project.getContentsImageUrl() != null) {
//            String[] fileName = project.getContentsImageUrl().split("/");
//            s3Service.delete(fileName[3]);
//        }
//        projectService.delete(id);
//        return "redirect:/projects/projectList";
//    }


}
