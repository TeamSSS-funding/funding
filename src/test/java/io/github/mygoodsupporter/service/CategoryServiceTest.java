package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.exception.CategoryAlreadyExistsException;
import io.github.mygoodsupporter.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    @Test
    void getCategoriesTest() {
        //when
        List<Category> categories = categoryService.getCategories();

        //then
        then(categoryMapper).should().getCategories();
    }

    @Test
    void getCategoryByIdTest() {
        //given
        Long categoryId = 1L;
        Category category = new Category("영화");
        category.setId(categoryId);
        given(categoryMapper.getCategoryById(categoryId)).willReturn(category);

        //when
        Category found = categoryService.getCategoryById(categoryId);

        //then
        then(categoryMapper).should().getCategoryById(categoryId);
    }

    @Test
    void getCategoryByNameTest() {
        //given
        String name = "영화";
        Category category = new Category(name);
        given(categoryMapper.getCategoryByName(name)).willReturn(category);

        //when
        Category found = categoryService.getCategoryByName(name);

        //then
        then(categoryMapper).should().getCategoryByName(name);
    }

    @Test
    void createCategoryTest() {
        //given
        Category category = new Category("영화");
        given(categoryMapper.getCategoryByName(category.getName())).willReturn(null);

        doAnswer(invocationOnMock -> {
            Category argument = invocationOnMock.getArgument(0, Category.class);
            argument.setId(1L);
            return null;
        }).when(categoryMapper).insertCategory(category);

        //when
        categoryService.createCategory(category);

        //then
        assertThat(category.getId()).isNotNull();
        then(categoryMapper).should().insertCategory(category);
    }

    @Test
    void createDuplicatedCategoryTest() {
        //given
        Category category = new Category("영화");
        given(categoryMapper.getCategoryByName(category.getName())).willReturn(category);

        assertThatThrownBy(() -> {
            categoryService.createCategory(category);
        }).isInstanceOf(CategoryAlreadyExistsException.class);

    }

    @Test
    void updateCategoryTest() {
        //given
        Category category = new Category("영화");
        category.setId(1L);
        given(categoryMapper.getCategoryById(category.getId())).willReturn(category);

        //when
        categoryService.updateCategory(category);

        //then
        then(categoryMapper).should().getCategoryById(anyLong());
        then(categoryMapper).should().updateCategory(any(Category.class));
    }

    @Test
    void deleteCategoryTest() {
        //given
        Long categoryId = 1L;
        given(categoryMapper.getCategoryById(1L)).willReturn(new Category("영화"));

        //when
        categoryService.deleteCategory(categoryId);

        //then
        then(categoryMapper).should().deleteCategory(categoryId);
    }
}