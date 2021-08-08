package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<Item> getItemsByProjectId(Long projectId);
    Item getItemById(Long itemId);

    void insertItem(Item item);
    void updateItem(Item item);
    void deleteItem(Long itemId);
}
