package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.domain.user.User;
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

    //프로젝트 정보 입력
    public Project projectRequest(Project pdto) {
        projectMapper.insert(pdto);
        return pdto;
    }

    @Transactional
    public Long openProject(Project project) {
        projectMapper.insert(project);
        return project.getId();
    }

    @Transactional
    public void supportProject(String username, Long projectId, int amount) {
        User user = userMapper.getUserByUsername(username);
        Project project = projectMapper.getProjectById(projectId);

        project.supportProject(amount);

        projectMapper.update(project);

    }

    public List<Project> getProjects() {
        return projectMapper.getProjects();
    }

    public Project getProjectById(Long id) {
        return projectMapper.getProjectById(id);
    }

}
