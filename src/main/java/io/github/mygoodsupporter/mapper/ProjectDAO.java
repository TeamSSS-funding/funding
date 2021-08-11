package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDAO {

    List<Project> getProjects();

    int insert(Project project);
    void update(Project project);

    List<Project> projectList(Project project);

    Project getProjectById(Long projectId);

    int projectUpdateProcess(Project project);

    void delete(Long id);
}
