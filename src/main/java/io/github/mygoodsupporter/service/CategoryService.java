package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Category;
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
    public void insertCategory(Category category) {
        categoryMapper.insertCategory(category);
    }

    @Transactional
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        categoryMapper.deleteCategory(categoryId);
    }
}
