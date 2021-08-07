package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> getProjects();
    List<Project> getProjectsByUserId(Long userId);

    Project getProjectById(Long projectId);

    void insertProject(Project project);
    void updateProject(Project project);
    void deleteProject(Long projectId);
}