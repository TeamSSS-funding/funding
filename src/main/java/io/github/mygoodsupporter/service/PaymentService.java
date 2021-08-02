package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Cardinfo;
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

    public int cardRegister(Cardinfo cardinfo) {

        return paymentMapper.insertCard(cardinfo);
    }


    public List<Cardinfo> cardList(Cardinfo cardinfo) {
        return paymentMapper.cardList(cardinfo);
    }
}
