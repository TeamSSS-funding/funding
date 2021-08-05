package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.exception.AddressNotFoundException;
import io.github.mygoodsupporter.mapper.AddressMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @InjectMocks
    AddressService addressService;

    @Mock
    AddressMapper addressMapper;

    @Test
    @DisplayName("사용자의 주소를 찾아올 수 있어야합니다")
    public void should_fetch_address_list_by_user_id() {
        //when
        addressService.getAddressesByUserId(1L);
        //then
        then(addressMapper).should(times(1)).getAddressesByUserId(anyLong());
    }

    @Test
    @DisplayName("주소는 식별자를 이용해 찾아올 수 있습니다")
    public void should_fetch_address_by_address_id() {
        //when
        addressService.getAddressById(1L);
        //then
        then(addressMapper).should(times(1)).getAddressById(anyLong());
    }

    @Test
    @DisplayName("주소를 생성합니다")
    public void should_create_address() {
        //when
        addressService.createAddress(1L, "city", "address", "zip");

        //then
        then(addressMapper).should().insertAddress(any(Address.class));
    }

    @Test
    @DisplayName("주소를 업데이트 할 수 있어야 합니다")
    public void should_update_address() {
        //given
        Long addressId = 1L;
        Address address = addressFixture(1L);
        address.setId(1L);

        given(addressMapper.getAddressById(addressId)).willReturn(address);

        //when
        addressService.updateAddress(addressId,
                "city test", "street test", "zipcode test");

        //then
        then(addressMapper).should().updateAddress(any(Address.class));
    }

    @Test
    @DisplayName("존재하지 않는 주소를 업데이트하려고 할 때 에러가 발생합니다")
    public void should_throw_error_when_deleting_none_exists_address() {
        //given
        Long addressId = 1L;
        given(addressMapper.getAddressById(addressId)).willReturn(null);

        //when
        assertThatThrownBy(() -> {
            addressService.updateAddress(addressId,
                    "city update", "street update ", "zip update");

        }).isInstanceOf(AddressNotFoundException.class);

    }

    @Test
    @DisplayName("주소를 삭제 할 수 있습니다")
    public void should_delete_address() {
        //given
        Long addressId = 1L;
        Address address = addressFixture(1L);
        address.setId(1L);

        given(addressMapper.getAddressById(addressId)).willReturn(address);

        //when
        addressService.deleteAddress(addressId);

        //then
        then(addressMapper).should().deleteAddress(addressId);
    }

    @Test
    @DisplayName("존재하지 않는 주소를 삭제하려고 한다면 에러가 발생합니다")
    public void should_throw_exception_when_deleting_none_exist_address() {
        //given
        Long addressId = 1L;
        given(addressMapper.getAddressById(addressId)).willReturn(null);

        //when
        assertThatThrownBy(() -> {
            addressService.deleteAddress(addressId);
        }).isInstanceOf(AddressNotFoundException.class);

    }

    public Address addressFixture(Long userId) {
        return Address.builder()
                .userId(userId)
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
    }
}