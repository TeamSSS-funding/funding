package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.mapper.ProjectDAO;
import io.github.mygoodsupporter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;
    private final UserMapper userMapper;

    //프로젝트 정보 입력
    public Project projectRequest(Project pdto) {
        projectDAO.insert(pdto);
        return pdto;
    }

    @Transactional
    public Long openProject(Project project) {
        projectDAO.insert(project);
        return project.getId();
    }

    @Transactional
    public void supportProject(String memberId, String slug, int amount) {
        User user = userMapper.getUserByUsername(memberId);
        Project project = projectDAO.getProjectBySlug(slug);

        project.supportProject(amount);

        projectDAO.update(project);

    }

    public List<Project> getProjects() {
        return projectDAO.getProjects();
    }

    public Project getProjectById(Long id) {
        return projectDAO.getProjectById(id);
    }

    public Project getProjectBySlug(String slug) {
        return projectDAO.getProjectBySlug(slug);
    }
}
