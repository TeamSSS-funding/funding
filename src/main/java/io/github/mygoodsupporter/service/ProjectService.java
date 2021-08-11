package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.dto.ProjectDTO;
import io.github.mygoodsupporter.mapper.ProjectDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;
    private final S3Service s3Service;

    public List<Project> projectList(Project project) {
        return projectDAO.projectList(project);
    }

    //프로젝트 정보 입력
    public Project createProject(ProjectDTO dto) {

        Project project = Project.builder()
                .title(dto.getTitle())
                .userId(dto.getUserId())
                .categoryId(dto.getCategoryId())
                .build();

        projectDAO.insert(project);
        return project;
    }

    public Project getProjectById(Long projectId) {
        return projectDAO.getProjectById(projectId);
    }

    public int updateProces(Project project) {
        return projectDAO.projectUpdateProcess(project);
    }

    public void delete(Long id) {
        projectDAO.delete(id);
    }


    public void basicsUpdate(Long projectId) {
        projectDAO.update(projectId);
    }
}
