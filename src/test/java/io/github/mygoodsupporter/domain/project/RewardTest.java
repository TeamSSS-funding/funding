package io.github.mygoodsupporter.domain.project;

import org.junit.jupiter.api.Test;

public class RewardTest {

    @Test
    public void createReward() {
        Item item = new Item("병뚜껑", 1L);
        Item otherItem = new Item("텀블러", 1L);

        RewardItem rewardItem = new RewardItem(item, 2);
        RewardItem otherRewardItem = new RewardItem(otherItem, 3);

        Reward supporterSet = Reward.builder()
                .title("서포터 세트")
                .description("병뚜겅 x2, 텀블러 x3")
                .projectId(1L)
                .amount(3500)
                .build();
    }
}