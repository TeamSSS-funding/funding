package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectDAO {

    List<Project> getProjects();

    int insert(Project project);
    void update(Project project);

    List<Project> projectList(Project project);

    Project projectUpdate(Long id);

    int projectUpdateProcess(Project project);

    void delete(Long id);
}
