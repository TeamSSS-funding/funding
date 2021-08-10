package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.security.UserDetails;
import io.github.mygoodsupporter.service.ProjectService;
import io.github.mygoodsupporter.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final S3Service s3Service;
    private final ProjectService projectService;

    //프로젝트 신청 화면 요청 메이커
    @GetMapping(value="/projects/new")
    public String projectRequestPage() {
        return "projects/projectRequest";
    }

    //프로젝트 신청페이지 메이커
    @PostMapping(value="/projects/new")
    public String projectRequest(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Project project,
                                 Model model, @RequestParam("contentsImage") MultipartFile file) throws IOException, IOException{
        //현재 로그인된 아이디 가져옴
        project.setUserId(userDetails.getId());

        String imgPath = s3Service.upload(file);
        project.setContentsImageName(imgPath);

        projectService.projectRequest(project);

        model.addAttribute("projectList", project);
        return "redirect:/projects/projectList";
    }

    @GetMapping(value = "/projects/projectList")
    private String projectList(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Project project, Model model){
        project.setUserId(userDetails.getId());
        List<Project> projectList =  projectService.projectList(project);
        model.addAttribute("projectList", projectList);
        return "projects/projectList";
    }

    @GetMapping(value = "/projects/projectList/update/{id}")
    public String updatePage(@PathVariable("id") Long id, Model model){
        Project project = projectService.projectUpdate(id);
        model.addAttribute("project",project);
        return "projects/update";
    }

    @PostMapping(value = "/projects/projectList/update/{id}")
    public String update(@ModelAttribute Project updateProject,@RequestParam("contentsImage") MultipartFile file, @PathVariable("id") Long id) throws IOException {
        Project project = projectService.projectUpdate(id);
        if(file.getSize() == 0){
            updateProject.setContentsImageName(project.getContentsImageName());
        } else {
            String[] fileName = project.getContentsImageName().split("/");
            s3Service.delete(fileName[3]);

            String imgPath = s3Service.upload(file);
            updateProject.setContentsImageName(imgPath);
        }
        projectService.updateProces(updateProject);


        return "redirect:/projects/projectList";
    }

    @GetMapping(value = "/projects/projectList/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws IOException {
        Project project = projectService.projectUpdate(id);
        String[] fileName = project.getContentsImageName().split("/");
        s3Service.delete(fileName[3]);
        projectService.delete(id);
        return "redirect:/projects/projectList";
    }


}
