package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryServiceIntegrationTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void getCategoryList() {
        List<Category> categories = categoryService.getCategories();

        assertThat(categories).hasSizeGreaterThan(0);

    }

}