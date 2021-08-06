package io.github.mygoodsupporter.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Address {
    private int number;
    private String id;
    private String name;
    private String phone;
    private String postcode;
    private String road;
    private String jibun;
    private String detail;
    private String chamgo;

}
