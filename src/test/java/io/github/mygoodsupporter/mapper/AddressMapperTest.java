package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/fixture/mapper/AddressMapperTestFixture.sql")
public class AddressMapperTest {

    @Autowired
    AddressMapper addressMapper;

    @Test
    void getAddressesByUserId() {
        //given
        Long userId = 1L;
        //when
        List<Address> addresses = addressMapper.getAddressesByUserId(userId);

        //then
        assertThat(addresses).hasSize(2);
        assertThat(addresses.get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void getAddressById() {
        //given
        Long addressId = 1L;

        //when
        Address address = addressMapper.getAddressById(addressId);

        //then
        assertThat(address.getId()).isEqualTo(1L);
        assertThat(address.getUserId()).isEqualTo(1L);
        assertThat(address.getCity()).isEqualTo("city1");
        assertThat(address.getStreet()).isEqualTo("street1");
        assertThat(address.getZipcode()).isEqualTo("zipcode1");

    }

    @Test
    void insertAddress() {
        //when
        Address address = Address.builder()
                .userId(1L)
                .city("new city")
                .street("new street")
                .zipcode("new zipcode")
                .build();

        //given
        addressMapper.insertAddress(address);

        //then
        assertThat(address.getId()).isNotNull();

        Address saved = addressMapper.getAddressById(address.getId());
        assertThat(saved.getUserId()).isEqualTo(address.getUserId());
        assertThat(saved.getCity()).isEqualTo(address.getCity());
        assertThat(saved.getStreet()).isEqualTo(address.getStreet());
        assertThat(saved.getZipcode()).isEqualTo(address.getZipcode());
    }

    @Test
    void updateAddress() {
        //given
        Long addressId = 1L;
        Address address = Address.builder()
                .userId(1L)
                .city("different city")
                .street("different street")
                .zipcode("different zipcode")
                .build();
        address.setId(addressId);

        //when
        addressMapper.updateAddress(address);

        //then
        Address updated = addressMapper.getAddressById(addressId);
        assertThat(updated.getId()).isEqualTo(1L);
        assertThat(updated.getUserId()).isEqualTo(1L);
        assertThat(updated.getCity()).isEqualTo("different city");
        assertThat(updated.getStreet()).isEqualTo("different street");
        assertThat(updated.getZipcode()).isEqualTo("different zipcode");
    }

    @Test
    void deleteAddress() {
        //given
        Long addressId = 1L;

        //when
        addressMapper.deleteAddress(addressId);

        //then
        Address address = addressMapper.getAddressById(addressId);
        assertThat(address).isNull();
    }
}