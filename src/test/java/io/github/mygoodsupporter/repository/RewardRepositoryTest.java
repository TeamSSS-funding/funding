package io.github.mygoodsupporter.repository;

import io.github.mygoodsupporter.domain.project.Item;
import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.domain.project.RewardItem;
import io.github.mygoodsupporter.mapper.ItemMapper;
import io.github.mygoodsupporter.mapper.RewardMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@SqlGroup({
        @Sql("/fixture/project/project.fixture.sql"),
        @Sql("/fixture/project/reward.fixture.sql")
})
public class RewardRepositoryTest {

    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    RewardMapper rewardMapper;

    @Autowired
    ItemMapper itemMapper;

    @Test
    void should_fetch_reward_list_by_projectId() {

        List<Reward> rewards = rewardRepository.getRewardsByProjectId(1L);

        assertThat(rewards).hasSize(3);
        assertThat(rewards.get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void should_fetch_reward_by_id() {
        Reward reward = rewardRepository.getRewardById(3L);

        assertThat(reward.getId()).isEqualTo(3L);
        assertThat(reward.getProjectId()).isEqualTo(1L);
        assertThat(reward.getTitle()).isEqualTo("병뚜껑과 텀블러 세트");
        assertThat(reward.getDescription()).isEqualTo("병뚜껑 x 2 텀블러 x1");
        assertThat(reward.getAmount()).isEqualTo(50000);

        assertThat(reward.getItems()).hasSize(2);
        assertThat(reward.getItems().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void should_save_reward() {
        //given

        Reward newReward = Reward.builder()
                .projectId(1L)
                .title("디럭스 패키지")
                .description("디럭스 패키지")
                .amount(45000)
                .build();
        Item bottleTop = itemMapper.getItemById(1L);
        Item tumbler = itemMapper.getItemById(2L);
        newReward.addItem(bottleTop, 3);
        newReward.addItem(tumbler, 2);

        //when
        rewardRepository.saveReward(newReward);

        //then
        Reward storedReward = rewardMapper.getRewardById(newReward.getId());
        assertThat(storedReward.getId()).isNotNull();
        assertThat(storedReward.getProjectId()).isEqualTo(newReward.getProjectId());
        assertThat(storedReward.getTitle()).isEqualTo(newReward.getTitle());
        assertThat(storedReward.getDescription()).isEqualTo(newReward.getDescription());
        assertThat(storedReward.getAmount()).isEqualTo(newReward.getAmount());

        assertThat(storedReward.getItems()).hasSameSizeAs((newReward.getItems()));
        assertThat(storedReward.getItems()).containsAll(newReward.getItems());
    }

    @Test
    void should_update_reward_and_reward_item() {
        //given
        Long rewardId = 1L;
        Reward bottleTop = rewardRepository.getRewardById(rewardId);
        bottleTop.setAmount(2500);
        RewardItem rewardItem = bottleTop.getItems().get(0);
        rewardItem.setQuantity(10);
        //when
        rewardRepository.updateReward(bottleTop);

        //then
        Reward stored = rewardMapper.getRewardById(bottleTop.getId());

        assertThat(stored.getAmount()).isEqualTo(2500);
        assertThat(stored.getItems()).hasSize(1);
        assertThat(stored.getItems().get(0).getQuantity()).isEqualTo(10);
    }

    @Test
    void should_update_reward_with_adding_reward_items() {
        //given
        Long rewardId = 1L;
        Reward reward = rewardRepository.getRewardById(rewardId);
        Item tumbler = itemMapper.getItemById(2L);

        //when
        reward.addItem(tumbler, 3);
        rewardRepository.updateReward(reward);

        //then
        Reward stored = rewardMapper.getRewardById(reward.getId());
        assertThat(stored.getItems()).hasSize(2);

        assertThat(stored.getItems()).containsAll(reward.getItems());
    }

    @Test
    void should_update_reward_deleting_none_exists_reward_item() {
        //given
        Long rewardId = 3L;
        Reward reward = rewardRepository.getRewardById(rewardId);

        //when
        reward.getItems().remove(0);
        rewardRepository.updateReward(reward);

        //then
        Reward stored = rewardMapper.getRewardById(reward.getId());
        assertThat(stored.getItems()).hasSize(1);

    }

    @Test
    void should_delete_reward() {
        //given
        Long rewardId = 3L;

        //when
        rewardRepository.deleteReward(rewardId);

        //then
        Reward deleted = rewardRepository.getRewardById(rewardId);

        assertThat(deleted).isNull();
    }
}