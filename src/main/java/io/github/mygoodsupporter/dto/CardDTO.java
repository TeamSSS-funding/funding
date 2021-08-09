package io.github.mygoodsupporter.dto;

import io.github.mygoodsupporter.domain.Card;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CardDTO {

    private Long id;
    private Long userId;
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String expiredDate;
    @NotBlank
    private String cardPassword;
    @NotBlank
    private String dateOfBirth;

    public Card toCard(){
        return Card.builder().cardNumber(cardNumber)
                .cardPassword(cardPassword)
                .dateOfBirth(dateOfBirth)
                .expiredDate(expiredDate)
                .userId(userId)
                .build();
    }
}
