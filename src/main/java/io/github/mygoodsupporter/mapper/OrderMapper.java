package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    void insertOrder(Order order);
    Order getById(Order id);
    void changeStatus(Order id);
}
