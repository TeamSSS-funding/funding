package io.github.mygoodsupporter.domain.project;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    public void createCategory() {
        Category category = new Category("영화");

        assertThat(category.getName()).isEqualTo("영화");
    }
}