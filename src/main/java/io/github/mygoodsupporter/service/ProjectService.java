package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.exception.ProjectNotFoundException;
import io.github.mygoodsupporter.mapper.ProjectMapper;
import io.github.mygoodsupporter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;

    public List<Project> getProjects() {
        return projectMapper.getProjects();
    }

    public List<Project> getProjectsByUserId(Long userId) {
        return projectMapper.getProjectsByUserId(userId);
    }

    public Project getProjectById(Long projectId) {
        return projectMapper.getProjectById(projectId);
    }


    //프로젝트 정보 입력
    @Transactional
    public Project projectRequest(Project project) {
        projectMapper.insertProject(project);
        return project;
    }

    @Transactional
    public Long createProject(Long userId, Long categoryId, String subtitle) {

        Project project = new Project(userId, categoryId, subtitle);
        projectMapper.insertProject(project);
        return project.getId();
    }

    @Transactional
    public void updateProject(Project project) {
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

    @Transactional
    public void supportProject(String username, Long projectId, int amount) {
        User user = userMapper.getUserByUsername(username);
        Project project = projectMapper.getProjectById(projectId);

        project.supportProject(amount);

        projectMapper.updateProject(project);
    }
}
