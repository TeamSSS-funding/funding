package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> getProjects();
    Project getProjectById(Long id);

    void insertProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long projectId);
}
