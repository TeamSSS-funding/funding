package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.domain.project.ProjectStatus;
import io.github.mygoodsupporter.exception.ProjectNotFoundException;
import io.github.mygoodsupporter.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Sql("/fixture/project/project.test.sql")
public class ProjectServiceIntegrationTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;


    @Test
    void createProject() {
        //given
        Long userId = 1L;
        Long categoryId = 1L;
        String subtitle = "new Project";

        //when
        Long newProjectId = projectService.createProject(userId, categoryId, subtitle);

        //then
        assertThat(newProjectId).isNotNull();

        Project saved = projectMapper.getProjectById(newProjectId);
        assertThat(saved.getUserId()).isEqualTo(userId);
        assertThat(saved.getCategoryId()).isEqualTo(categoryId);
        assertThat(saved.getSubtitle()).isEqualTo(subtitle);

        assertThat(saved.getStatus()).isEqualTo(ProjectStatus.PREPARING);
    }


    @Test
    public void should_delete_project() {
        Long id = 1L;

        projectService.deleteProject(id);

        Project deleted = projectMapper.getProjectById(id);

        assertThat(deleted).isNull();
    }

    @Test
    public void should_throw_error_when_deleting_none_exists_project() {

        Long id = 1000L;

        assertThatThrownBy(() -> {
            projectService.deleteProject(id);
        }).isInstanceOf(ProjectNotFoundException.class);
    }
}