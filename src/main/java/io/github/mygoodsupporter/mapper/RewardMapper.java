package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.domain.project.RewardItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RewardMapper {

    List<Reward> getRewardsByProjectId(Long projectId);
    Reward getRewardById(Long rewardId);

    void insertReward(Reward reward);
    void updateReward(Reward reward);
    void deleteReward(Long rewardId);

    RewardItem getRewardItemById(Long rewardItemId);

    void insertRewardItem(RewardItem item);
    void updateRewardItem(RewardItem item);
    void deleteRewardItem(Long rewardItemId);
}
