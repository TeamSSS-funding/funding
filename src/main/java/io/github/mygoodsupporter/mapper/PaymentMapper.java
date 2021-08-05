package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    List<Card> getCardListByUserId(Long userId);

    Card getCardById(Long id);

    void insertCard(Card card);
    void updateCard(Card card);
    void deleteCard(Long id);
}
