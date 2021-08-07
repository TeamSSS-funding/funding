package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Category;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/fixture/category/category.test.sql")
public class CategoryMapperTest {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void getCategories() {
        List<Category> categories = categoryMapper.getCategories();

        assertThat(categories).hasSize(3);
    }

    @Test
    void getCategoryByName() {
        String name = "cinema";
        Category category = categoryMapper.getCategoryByName(name);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isNotNull();
        assertThat(category.getName()).isEqualTo(name);
    }

    @Test
    void getCategoryById() {
        //given
        Long id = 1L;

        //when
        Category category = categoryMapper.getCategoryById(id);

        //then
        assertThat(category.getId()).isEqualTo(1L);
        assertThat(category.getName()).isEqualTo("art");
    }

    @Test
    void insertCategory() {
        //given
        Category category = new Category("journalism");

        //when
        categoryMapper.insertCategory(category);

        //then
        assertThat(category.getId()).isNotNull();
    }

    @Test
    void updateCategory() {
        //given
        Category category = categoryMapper.getCategoryByName("cinema");

        //when
        category.setName("film");
        categoryMapper.updateCategory(category);

        //then
        Category updated = categoryMapper.getCategoryById(category.getId());
        assertThat(updated.getName()).isEqualTo(category.getName());
    }

    @Test
    void deleteCategory() {
        //given
        Category category = categoryMapper.getCategoryByName("cinema");

        //when
        categoryMapper.deleteCategory(category.getId());

        //then
        Category deleted = categoryMapper.getCategoryById(category.getId());
        assertThat(deleted).isNull();
    }
}