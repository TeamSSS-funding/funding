package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Item;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SqlGroup({
        @Sql("/fixture/project/project.test.sql"),
        @Sql("/fixture/project/item.test.sql")
})
public class ItemMapperTest {

    @Autowired
    ItemMapper itemMapper;

    @Test
    void getItemById() {
        //when
        Item item = itemMapper.getItemById(1L);

        //then
        assertThat(item.getId()).isEqualTo(1L);
        assertThat(item.getProjectId()).isEqualTo(1L);
        assertThat(item.getTitle()).isEqualTo("쿠키");
    }

    @Test
    void getItemsByProjectId() {
        //when
        List<Item> items = itemMapper.getItemsByProjectId(1L);

        //then
        assertThat(items).hasSize(2);
        assertThat(items.get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void insertItem() {
        //given
        Item item = Item.builder().title("Badge").projectId(2L).build();

        //when
        itemMapper.insertItem(item);

        //then
        assertThat(item.getId()).isNotNull();
    }

    @Test
    void updateItem() {
        //given
        Item item = itemMapper.getItemById(1L);
        item.setTitle("Pie");

        //when
        itemMapper.updateItem(item);

        //then
        Item updated = itemMapper.getItemById(item.getId());
        assertThat(updated.getTitle()).isEqualTo("Pie");
    }

    @Test
    void deleteItem() {
        //when
        itemMapper.deleteItem(1L);

        //then
        Item deleted = itemMapper.getItemById(1L);

        assertThat(deleted).isNull();
    }
}