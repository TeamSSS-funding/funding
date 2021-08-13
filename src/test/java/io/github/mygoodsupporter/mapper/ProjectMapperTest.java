package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Project;
import io.github.mygoodsupporter.domain.project.ProjectStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/fixture/project/project.fixture.sql")
public class ProjectMapperTest {

    @Autowired
    ProjectMapper projectMapper;

    @Test
    void getProjectsByUserId() {
        //given
        Long userId = 1L;

        //when
        List<Project> projects = projectMapper.getProjectsByUserId(userId);

        //then
        assertThat(projects).hasSize(2);
        Project project = projects.get(0);
        assertThat(project.getId()).isEqualTo(1L);
        assertThat(project.getUserId()).isEqualTo(1L);
        assertThat(project.getCategoryId()).isEqualTo(1L);
        assertThat(project.getTitle()).isEqualTo("title");
        assertThat(project.getSubtitle()).isEqualTo("subtitle");
        assertThat(project.getStatus()).isEqualTo(ProjectStatus.PREPARING);
    }

    @Test
    void getProjectById() {
        //given
        Long projectId = 1L;

        //when
        Project project = projectMapper.getProjectById(projectId);

        //then
        assertThat(project.getId()).isEqualTo(1L);
        assertThat(project.getUserId()).isEqualTo(1L);
        assertThat(project.getCategoryId()).isEqualTo(1L);
        assertThat(project.getTitle()).isEqualTo("title");
        assertThat(project.getSubtitle()).isEqualTo("subtitle");
        assertThat(project.getStatus()).isEqualTo(ProjectStatus.PREPARING);
    }

    @Test
    void insertProject() {
        //given
        Project project = Project.createProject(1L, 2L, "new project");

        //when
        projectMapper.insertProject(project);

        //then
        assertThat(project.getId()).isNotNull();
    }

    @Test
    void updateProject() {
        //given
        Long projectId = 2L;
        Project project = projectMapper.getProjectById(projectId);
        project.setCategoryId(2L);
        project.setTitle("update project");

        //when
        projectMapper.updateProject(project);

        //then
        Project updated = projectMapper.getProjectById(projectId);

        assertThat(updated.getCategoryId()).isEqualTo(2L);
        assertThat(updated.getTitle()).isEqualTo("update project");
    }

    @Test
    void deleteProject() {
        //given
        Long projectId = 2L;

        //when
        projectMapper.deleteProject(projectId);

        //then
        Project deleted = projectMapper.getProjectById(projectId);

        assertThat(deleted).isNull();
    }
}