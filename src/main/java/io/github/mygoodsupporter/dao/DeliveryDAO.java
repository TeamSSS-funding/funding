package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryDAO {

    // 배송지 추가
    int insert(Address address);

    // 배송지 목록 조회
    List<Address> list(Address address);
}
