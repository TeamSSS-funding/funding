package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Cardinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {

    int insertCard(Cardinfo cardinfo);

    List<Cardinfo> cardList(Cardinfo cardinfo);

    Cardinfo getCardInfoById(Long id);
}
