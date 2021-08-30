package io.github.mygoodsupporter.service.project;

import io.github.mygoodsupporter.dto.ProjectDetails;
import io.github.mygoodsupporter.mapper.ProjectDetailsMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectQueryService {

    private final ProjectDetailsMapper projectDetailsMapper;

    public ProjectQueryService(ProjectDetailsMapper projectDetailsMapper){
        this.projectDetailsMapper = projectDetailsMapper;
    }

    public ProjectDetails getById(Long id){
        ProjectDetails projectDetails = projectDetailsMapper.getById(id);
        return projectDetails;
    }
}
