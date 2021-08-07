package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.exception.CategoryAlreadyExistsException;
import io.github.mygoodsupporter.exception.CategoryNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Sql("/fixture/category/category.test.sql")
public class CategoryServiceIntegrationTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void getCategoryList() {
        List<Category> categories = categoryService.getCategories();

        assertThat(categories).isNotEmpty();
    }

    @Test
    void getCategoryByIdTest() {
        //given
        Long categoryId = 1L;

        //when
        Category category = categoryService.getCategoryById(categoryId);

        //then
        assertThat(category.getId()).isEqualTo(categoryId);
        assertThat(category.getName()).isEqualTo("영화");
    }

    @Test
    void getCategoryByNameTest() {
        //given
        String categoryName = "영화";

        //when
        Category category = categoryService.getCategoryByName(categoryName);

        //then
        assertThat(category.getId()).isNotNull();
        assertThat(category.getName()).isEqualTo(categoryName);
    }

    @Test
    void createCategoryTest() {
        //given
        Category category = new Category("패션");

        //when
        categoryService.createCategory(category);

        //then
        assertThat(category.getId()).isNotNull();
    }

    @Test
    @DisplayName("이미 존재하는 이름으로 카테고리를 생성할 때 오류가 발생합니다")
    void should_throw_exception_creating_category_with_exist_name() {
        //given
        Category category = new Category("영화");

        assertThatThrownBy(() -> {
            categoryService.createCategory(category);
        }).isInstanceOf(CategoryAlreadyExistsException.class);
    }

    @Test
    void updateCategoryTest() {
        //given
        Category category = new Category("영화");
        category.setId(1L);

        //when
        category.setName("영화음악");
        categoryService.updateCategory(category);

        //then
        Category updated = categoryService.getCategoryById(category.getId());

        assertThat(updated).isEqualTo(category);
    }

    @Test
    void deleteCategoryTest() {
        //given
        Long categoryId = 1L;

        //when
        categoryService.deleteCategory(categoryId);

        //then
        Category deleted = categoryService.getCategoryById(categoryId);
        assertThat(deleted).isNull();
    }

    @Test
    void should_throw_error_when_delete_none_exists_category() {
        //given
        Long categoryId = -1L;

        assertThatThrownBy(() -> {
            categoryService.deleteCategory(categoryId);
        }).isInstanceOf(CategoryNotFoundException.class);
    }
}