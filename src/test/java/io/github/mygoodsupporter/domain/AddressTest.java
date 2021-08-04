package io.github.mygoodsupporter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    @DisplayName("주소 객체를 생성하는 방법을 보여줍니다.")
    public void createAddressTest() {
        Address address = Address.builder()
                .userId(1L)
                .city("city")
                .street("street")
                .zipcode("23350")
                .build();

        Address other = new Address(1L, "city", "street", "23350");
    }
}