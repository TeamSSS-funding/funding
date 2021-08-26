package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Item;
import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.domain.project.RewardItem;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@SqlGroup({
    @Sql("/fixture/project/project.fixture.sql"),
    @Sql("/fixture/project/reward.fixture.sql")
})
public class RewardMapperTest {

    @Autowired
    RewardMapper rewardMapper;
    @Autowired
    ItemMapper itemMapper;

    @Test
    void getRewardsByProjectId() {
        List<Reward> rewards = rewardMapper.getRewardsByProjectId(1L);

        assertThat(rewards).hasSize(3);

    }

    @Test
    void getRewardById() {
        //when
        Reward reward = rewardMapper.getRewardById(3L);

        //then
        assertThat(reward.getId()).isEqualTo(3L);
        assertThat(reward.getProjectId()).isEqualTo(1L);
        assertThat(reward.getTitle()).isEqualTo("병뚜껑과 텀블러 세트");
        assertThat(reward.getAmount()).isEqualTo(50000);
        assertThat(reward.getDescription()).contains("병뚜껑", "텀블러");

        assertThat(reward.getItems()).hasSize(2);
        assertThat(reward.getItems().get(0)).hasNoNullFieldsOrProperties();
        assertThat(reward.getItems().get(0).getId()).isEqualTo(3L);
        assertThat(reward.getItems().get(0).getRewardId()).isEqualTo(3L);
        assertThat(reward.getItems().get(0).getQuantity()).isEqualTo(2);
        assertThat(reward.getItems().get(0).getItem().getId()).isEqualTo(1L);
        assertThat(reward.getItems().get(0).getItem().getProjectId()).isEqualTo(1L);
        assertThat(reward.getItems().get(0).getItem().getTitle()).isEqualTo("병뚜껑");

    }

    @Test
    void insertReward() {
        //given
        Reward reward = Reward.builder()
                .title("서포터 세트")
                .description("병뚜겅 x2, 텀블러 x3")
                .projectId(1L)
                .amount(3500)
                .build();

        //when
        rewardMapper.insertReward(reward);

        //then
        assertThat(reward.getId()).isNotNull();
    }

    @Test
    void updateReward() {
        //given
        Reward reward = Reward.builder()
                .projectId(1L)
                .title("병뚜껑")
                .description("병뚜껑")
                .amount(10000)
                .build();
        reward.setId(1L);

        //when
        reward.setTitle("병뚜껑 5개");
        reward.setDescription("병뚜껑 5개");
        reward.setAmount(35000);

        rewardMapper.updateReward(reward);

        //then
        Reward updated = rewardMapper.getRewardById(1L);
        assertThat(updated.getTitle()).contains("5개");
        assertThat(updated.getDescription()).contains("5개");
        assertThat(updated.getAmount()).isEqualTo(35000);
    }

    @Test
    void deleteReward() {
        //when
        rewardMapper.deleteReward(3L);

        //then
        Reward reward = rewardMapper.getRewardById(3L);
        assertThat(reward).isNull();
    }

    @Test
    void getRewardItemsByRewardId() {
        List<RewardItem> rewardItems = rewardMapper.getRewardItemsByRewardId(3L);

        assertThat(rewardItems).hasSize(2);
    }

    @Test
    void getRewardItemById() {
        RewardItem rewardItem = rewardMapper.getRewardItemById(4L);

        assertThat(rewardItem.getId()).isEqualTo(4L);
        assertThat(rewardItem.getQuantity()).isEqualTo(1);
        assertThat(rewardItem.getItem().getId()).isEqualTo(2L);
        assertThat(rewardItem.getItem().getProjectId()).isEqualTo(1L);
        assertThat(rewardItem.getItem().getTitle()).isEqualTo("텀블러");
    }

    @Test
    void insertRewardItem() {
        //given
        Item item = itemMapper.getItemById(1L);
        RewardItem rewardItem = RewardItem.builder()
                .item(item)
                .quantity(3)
                .build();
        rewardItem.setRewardId(1L);
        //when
        rewardMapper.insertRewardItem(rewardItem);

        //then
        assertThat(rewardItem.getId()).isNotNull();
    }

    @Test
    void updateRewardItem() {
        //given
        RewardItem rewardItem = rewardMapper.getRewardItemById(4L);

        //when
        rewardItem.setQuantity(100);
        rewardMapper.updateRewardItem(rewardItem);

        //then
        RewardItem updated = rewardMapper.getRewardItemById(4L);
        assertThat(updated.getQuantity()).isEqualTo(100);
    }

    @Test
    void deleteRewardItem() {
        rewardMapper.deleteRewardItem(4L);

        RewardItem deletedItem = rewardMapper.getRewardItemById(4L);
        assertThat(deletedItem).isNull();
    }
}