package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.exception.CategoryAlreadyExistsException;
import io.github.mygoodsupporter.exception.CategoryNotFoundException;
import io.github.mygoodsupporter.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;

    public List<Category> getCategories() {
        return categoryMapper.getCategories();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryMapper.getCategoryById(categoryId);
    }

    public Category getCategoryByName(String categoryName) {
        return categoryMapper.getCategoryByName(categoryName);
    }

    @Transactional
    public void createCategory(Category category) {

        isNotExists(category);
        categoryMapper.insertCategory(category);
    }

    private void isNotExists(Category category) {
        Category found = categoryMapper.getCategoryByName(category.getName());
        if (found != null) {
            throw new CategoryAlreadyExistsException();
        }
    }

    @Transactional
    public void updateCategory(Category category) {

        isExists(category.getId());
        categoryMapper.updateCategory(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {

        isExists(categoryId);
        categoryMapper.deleteCategory(categoryId);
    }

    private void isExists(Long categoryId) {
        if (categoryId == null) {
            throw new NullPointerException();
        }

        Category category = categoryMapper.getCategoryById(categoryId);

        if (category == null) {
            throw new CategoryNotFoundException();
        }
    }
}
