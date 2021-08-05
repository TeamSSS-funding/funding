package io.github.mygoodsupporter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddressForm {


    @NotBlank(message = "시군구를 입력해주세요")
    private String city;

    @NotBlank(message = "상세 주소를 입력해주세요")
    private String street;

    @NotBlank(message = "우편번호를 입력해주세요")
    private String zipcode;
}
