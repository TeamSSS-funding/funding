package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.Cardinfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void cardList() {
        /*
           <select id="cardList" parameterType="Cardinfo" resultType="Cardinfo">
                SELECT * FROM cardinfo WHERE userId = #{userId}
            </select>
         */
        Cardinfo info = new Cardinfo();
        info.setUserId("3");


        List<Cardinfo> infos = paymentMapper.cardList(info);
        Long userId = 3L;
        assertThat(userId.toString()).isEqualTo("3");
        assertThat(infos).hasSizeGreaterThan(0);
        assertThat(infos.get(0)).hasAllNullFieldsOrProperties();
    }

    @Test
    public void getById() {
        Cardinfo info = paymentMapper.getCardInfoById(20L);

        assertThat(info.getId()).isEqualTo(20L);
        assertThat(info.getCard_number()).isEqualTo("1212-1212-1212-1212");
        assertThat(info.getExpired_date()).isEqualTo("12/50");

    }
}