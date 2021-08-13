package io.github.mygoodsupporter.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class AddressForm {

    private Long id;

    @NotBlank(message = "수령인을 입력해주세요")
    private String name;

    @NotBlank(message = "우편번호를 입력해주세요")
    private String postcode;

    @NotBlank(message = "전화번호를 입력해주세요")
    private String phone;

    @NotBlank(message = "주소를 입력해주세요")
    private String road;

    @NotBlank(message = "주소를 입력해주세요")
    private String jibun;

    @NotBlank(message = "주소를 입력해주세요")
    private String detail;

    @NotBlank(message = "주소를 입력해주세요")
    private String chamgo;
}
