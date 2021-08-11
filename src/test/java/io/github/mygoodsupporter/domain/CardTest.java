package io.github.mygoodsupporter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    public void createCardTest() {
        Card card = Card.builder()
                .userId(30L)
                .cardNumber("1111-2222-5555-6666")
                .cardPassword("12")
                .expiredDate("890803")
                .dateOfBirth("05/26")
                .build();
    }

    @Test
    @DisplayName("두 카드가 같은 카드인지 비교")
    public void sameCard() {
        Card card = Card.builder()
                .userId(30L)
                .cardNumber("1111-2222-5555-6666")
                .cardPassword("12")
                .expiredDate("890803")
                .dateOfBirth("05/26")
                .build();

        Card otherCard = Card.builder()
                .userId(1L)
                .cardNumber("1111-2222-5555-6666")
                .cardPassword("12")
                .expiredDate("890803")
                .dateOfBirth("05/26")
                .build();

        assertThat(card).isEqualTo(otherCard);

    }

}