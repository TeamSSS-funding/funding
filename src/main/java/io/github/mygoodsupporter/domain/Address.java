package io.github.mygoodsupporter.domain;

import lombok.*;

@EqualsAndHashCode(of={"name", "phone", "postcode", "road", "jibun", "detail", "chamgo"})
@NoArgsConstructor
@Getter @Setter
public class Address {
    private Long id;
    private Long userId;
    private String name;
    private String phone;
    private String postcode;
    private String road;
    private String jibun;
    private String detail;
    private String chamgo;

    @Builder
    public Address(Long id, Long userId, String name, String phone, String postcode, String road, String jibun, String detail, String chamgo) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.road = road;
        this.jibun = jibun;
        this.detail = detail;
        this.chamgo = chamgo;
    }

}
