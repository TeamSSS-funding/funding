package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.dto.ProjectDTO;
import io.github.mygoodsupporter.exception.ProjectNotFoundException;
import io.github.mygoodsupporter.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final S3Service s3Service;

    public List<Project> getProjectsByUserId(Long userId) {
        return projectMapper.getProjectsByUserId(userId);
    }

    public Project getProjectById(Long projectId) {
        return projectMapper.getProjectById(projectId);
    }


    @Transactional
    public Long createProject(ProjectDTO dto) {

        Project project = Project.createProject(dto.getUserId(), dto.getCategoryId(), dto.getTitle());

        projectMapper.insertProject(project);
        return project.getId();
    }

    @Transactional
    public void updateProject(ProjectDTO dto) throws IOException {

        Project project = projectMapper.getProjectById(dto.getId());
        if(project == null){
            throw new ProjectNotFoundException("project id:" + dto.getId() + " is not found");
        }

        project.setCategoryId(dto.getCategoryId());
        project.setTitle(dto.getTitle());
        project.setSubtitle(dto.getSubtitle());
        project.setTargetAmount(dto.getTargetAmount());
        project.setStartDate(LocalDateTime.of(dto.getStartDate(), LocalTime.MIDNIGHT));
        project.setEndDate(LocalDateTime.of(dto.getEndDate(), LocalTime.MIDNIGHT));


        if (dto.getTitleImageFile().getSize() > 0) {
            if (project.getTitleImageUrl() == null) {
                String imgPath = s3Service.upload(dto.getTitleImageFile());
                project.setTitleImageUrl(imgPath);
            } else {
                String[] fileName = project.getTitleImageUrl().split("/");
                s3Service.delete(fileName[3]);

                String imgPath = s3Service.upload(dto.getTitleImageFile());
                project.setTitleImageUrl(imgPath);
            }
        }
        projectMapper.updateProject(project);
    }

    @Transactional
    public void deleteProject(Long projectId) {
        Project project = projectMapper.getProjectById(projectId);

        if (project == null) {
            throw new ProjectNotFoundException();
        }

        projectMapper.deleteProject(projectId);
    }
    
}
