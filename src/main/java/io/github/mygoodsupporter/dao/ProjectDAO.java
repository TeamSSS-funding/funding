package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDAO {

    List<Project> getProjects();
    Project getProjectById(Long id);
    Project getProjectBySlug(String slug);

    int insert(Project project);
    void update(Project project);
}
