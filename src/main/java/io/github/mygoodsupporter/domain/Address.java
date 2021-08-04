package io.github.mygoodsupporter.domain;

import lombok.*;

@EqualsAndHashCode(of={"city", "street", "zipcode"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Address {

    private Long id;
    private Long userId;
    private String city;
    private String street;
    private String zipcode;

    @Builder
    public Address(Long userId, String city, String street, String zipcode) {
        this.userId = userId;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
