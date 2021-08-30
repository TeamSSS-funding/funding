package io.github.mygoodsupporter.domain;

import io.github.mygoodsupporter.dto.CardDTO;
import lombok.*;
import org.springframework.util.Assert;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of={"cardNumber"})
public class Card extends CardDTO {
    private Long id;
    private Long userId;
    private String cardNumber;
    private String expiredDate;
    private String cardPassword;
    private String dateOfBirth;

    @Builder
    public Card(Long id,Long userId, String cardNumber, String expiredDate, String cardPassword, String dateOfBirth) {

        Assert.notNull(userId, "userid must not be null");
        this.id = id;
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.cardPassword = cardPassword;
        this.dateOfBirth = dateOfBirth;
    }
}
