package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.exception.AddressNotFoundException;
import io.github.mygoodsupporter.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AddressService {

    private final AddressMapper addressMapper;

    // 목록
    public List<Address> getAddressesByUserId(Long userId) {
        return addressMapper.getAddressesByUserId(userId);
    }

    // 상세
    public Address getAddressById(Long addressId) {
        return addressMapper.getAddressById(addressId);
    }

    // 추가
    @Transactional
    public void createAddress(Long userId, String name, String phone, String postcode, String road, String jibun, String detail, String chamgo) {
        Address address = Address.builder()
                .userId(userId)
                .name(name)
                .phone(phone)
                .postcode(postcode)
                .road(road)
                .jibun(jibun)
                .detail(detail)
                .chamgo(chamgo)
                .build();
        addressMapper.insertAddress(address);
    }

    // 수정
    @Transactional
    public void updateAddress(Long addressId, String name, String phone, String postcode, String road, String jibun, String detail, String chamgo) {
        Address address = addressMapper.getAddressById(addressId);
        if(address == null) {
            throw new AddressNotFoundException();
        }
        if(!isEmpty(name)) {
            address.setName(name);
        }
        if(!isEmpty(phone)) {
            address.setPhone(phone);
        }
        if(!isEmpty(postcode)) {
            address.setPostcode(postcode);
        }
        if(!isEmpty(road)) {
            address.setRoad(road);
        }
        if(!isEmpty(jibun)) {
            address.setJibun(jibun);
        }
        if(!isEmpty(detail)) {
            address.setDetail(detail);
        }
        if(!isEmpty(chamgo)) {
            address.setChamgo(chamgo);
        }
        addressMapper.updateAddress(address);
    }
    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    // 삭제
    @Transactional
    public void deleteAddress(Long addressId) {
        Address address = addressMapper.getAddressById(addressId);
        if(address == null) {
            throw new AddressNotFoundException();
        }
        addressMapper.deleteAddress(addressId);
    }

}
