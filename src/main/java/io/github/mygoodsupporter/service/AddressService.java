package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.exception.AddressNotFoundException;
import io.github.mygoodsupporter.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper addressMapper;

    public List<Address> getAddressesByUserId(Long userId) {
        return addressMapper.getAddressesByUserId(userId);
    }

    public Address getAddressById(Long addressId) {
        return addressMapper.getAddressById(addressId);
    }

    @Transactional
    public void createAddress(Long userId, String city, String street, String zipcode) {

        Address address = Address.builder()
                .userId(userId)
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();

        addressMapper.insertAddress(address);
    }

    @Transactional
    public void updateAddress(Long addressId, String city, String street, String zipcode) {
        Address address = addressMapper.getAddressById(addressId);

        if (address == null) {
            throw new AddressNotFoundException();
        }

        if (!isEmpty(city)) {
            address.setCity(city);
        }

        if (!isEmpty(street)) {
            address.setStreet(street);
        }

        if (!isEmpty(zipcode)) {
            address.setZipcode(zipcode);
        }

        addressMapper.updateAddress(address);
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    @Transactional
    public void deleteAddress(Long addressId) {
        Address address = addressMapper.getAddressById(addressId);

        if (address == null) {
            throw new AddressNotFoundException();
        }

        addressMapper.deleteAddress(addressId);
    }
}
