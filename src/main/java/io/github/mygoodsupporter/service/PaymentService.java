package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Card;
import io.github.mygoodsupporter.dto.CardDTO;
import io.github.mygoodsupporter.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentMapper paymentMapper;

    @Transactional
    public void createCard(CardDTO dto) {

        Card card = dto.toCard();
        paymentMapper.insertCard(card)
        ;
    }

    public Card getCardById(Long id){
        return paymentMapper.getCardById(id);
    }

    public List<Card> getCardList(Long userId) {
        return paymentMapper.getCardListByUserId(userId);
    }
    @Transactional
    public void updateCard(Card card){
        paymentMapper.updateCard(card);
    }
    @Transactional
    public void deleteCard(Long id){
        paymentMapper.deleteCard(id);
    }


}
