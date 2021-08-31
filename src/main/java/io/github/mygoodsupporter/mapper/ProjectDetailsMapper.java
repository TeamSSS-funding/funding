package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.dto.ProjectDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectDetailsMapper {

    ProjectDetails getById(Long id);
}
