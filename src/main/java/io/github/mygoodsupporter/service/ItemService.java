package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.project.Item;
import io.github.mygoodsupporter.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;

    public List<Item> getItemsByProjectId(Long projectId) {
        return itemMapper.getItemsByProjectId(projectId);
    }

    public Item getItemById(Long itemId) {
        return itemMapper.getItemById(itemId);
    }

    public void insertItem(Item item) {
        itemMapper.insertItem(item);
    }

    public void updateItem(Item item) {
        itemMapper.updateItem(item);
    }

    public void deleteItem(Long itemId) {
        itemMapper.deleteItem(itemId);
    }
}
