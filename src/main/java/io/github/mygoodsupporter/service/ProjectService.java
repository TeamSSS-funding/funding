package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Project;
import io.github.mygoodsupporter.mapper.ProjectDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;

    public List<Project> projectList(Project project) {
        return projectDAO.projectList(project);
    }

    //프로젝트 정보 입력
    public Project projectRequest(Project project) {
        projectDAO.insert(project);
        return project;
    }

    public Project projectUpdate(Long id) {
        return projectDAO.projectUpdate(id);
    }

    public int updateProces(Project project) {
        return projectDAO.projectUpdateProcess(project);
    }

    public void delete(Long id) {
        projectDAO.delete(id);
    }
}
