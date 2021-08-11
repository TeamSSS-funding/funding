package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> getCategories();
    Category getCategoryById(Long categoryId);
    Category getCategoryByName(String categoryName);

    void insertCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Long categoryId);
}
