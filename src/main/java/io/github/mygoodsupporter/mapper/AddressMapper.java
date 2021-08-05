package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    List<Address> getAddressesByUserId(Long userId);
    Address getAddressById(Long addressId);

    void insertAddress(Address address);
    void updateAddress(Address address);
    void deleteAddress(Long addressId);
}
