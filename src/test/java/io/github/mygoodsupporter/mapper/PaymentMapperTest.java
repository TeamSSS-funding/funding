package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Card;
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
@Sql("file:src/test/java/io/github/mygoodsupporter/mapper/PaymentMapperTest.sql")
public class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;


    @Test
    void getCardListByUserId() {
        //given
        Long userId = 1L;

        //when
        List<Card> cardList = paymentMapper.getCardListByUserId(userId);

        //then
        assertThat(cardList).hasSize(1);
    }

    @Test
    void getCardById() {
        //given
        Long cardId = 3L;
        //when
        Card card = paymentMapper.getCardById(cardId);
        //then
        assertThat(card.getId()).isEqualTo(3);
        assertThat(card.getUserId()).isEqualTo(1);
        assertThat(card.getCardNumber()).isEqualTo("1111-1111-1111-1111");
        assertThat(card.getExpiredDate()).isEqualTo("12/24");
        assertThat(card.getCardPassword()).isEqualTo("12");
        assertThat(card.getDateOfBirth()).isEqualTo("890803");

    }

    @Test
    void insertCard() {
        //given

        Card card = new Card(4L,"2222-2222-2222-2222", "12/25","12","900111");

        //when
        paymentMapper.insertCard(card);

        //then
        assertThat(card.getUserId()).isEqualTo(4);
        assertThat(card.getCardNumber()).isEqualTo("2222-2222-2222-2222");
        assertThat(card.getExpiredDate()).isEqualTo("12/25");
        assertThat(card.getCardPassword()).isEqualTo("12");
        assertThat(card.getDateOfBirth()).isEqualTo("900111");
    }

    @Test
    void updateCard() {

        //given
        Long cardId=3L;
        Card card = paymentMapper.getCardById(cardId);
        card.setCardNumber("2222-2222-2222-2223");
        card.setCardPassword("13");
        card.setExpiredDate("12/25");
        card.setDateOfBirth("900111");

        //when
        paymentMapper.updateCard(card);

        //then
        Card updated = paymentMapper.getCardById(cardId);
        assertThat(updated.getCardNumber()).isEqualTo("2222-2222-2222-2223");
        assertThat(updated.getCardPassword()).isEqualTo("13");
        assertThat(updated.getExpiredDate()).isEqualTo("12/25");
        assertThat(updated.getDateOfBirth()).isEqualTo("900111");

    }

    @Test
    void deleteCard() {
        //given
        Long cardId = 3L;

        //when
        paymentMapper.deleteCard(cardId);

        //then
        Card card = paymentMapper.getCardById(cardId);
        assertThat(card).isNull();

    }
}