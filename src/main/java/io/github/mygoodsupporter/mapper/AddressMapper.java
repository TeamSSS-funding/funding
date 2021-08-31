package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    // 목록
    List<Address> getAddressesByUserId(Long userId);

    // 상세
    Address getAddressById(Long addressId);

    // 등록
    void insertAddress(Address address);

    // 수정
    void updateAddress(Address address);

    // 삭제
    void deleteAddress(Long addressId);
}
