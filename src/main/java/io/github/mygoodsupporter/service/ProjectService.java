package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.dao.ProjectDAO;
import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;
    private final MemberDAO memberDAO;

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
        Member member = memberDAO.getMemberById(memberId);
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
