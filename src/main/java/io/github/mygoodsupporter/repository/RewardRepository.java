package io.github.mygoodsupporter.repository;

import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.domain.project.RewardItem;
import io.github.mygoodsupporter.exception.RewardNotFoundException;
import io.github.mygoodsupporter.mapper.ItemMapper;
import io.github.mygoodsupporter.mapper.RewardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RewardRepository {

    private final RewardMapper rewardMapper;
    private final ItemMapper itemMapper;

    public List<Reward> getRewardsByProjectId(Long projectId) {
        return rewardMapper.getRewardsByProjectId(projectId);
    };

    public Reward getRewardById(Long rewardId) {
        return rewardMapper.getRewardById(rewardId);
    }

    public Long saveReward(Reward reward) {
        rewardMapper.insertReward(reward);
        for (RewardItem item : reward.getItems()) {
            item.setRewardId(reward.getId());
            rewardMapper.insertRewardItem(item);
        }
        return reward.getId();
    }

    public void updateReward(Reward reward) {
        Reward found = rewardMapper.getRewardById(reward.getId());
        if (found == null) {
            throw new RewardNotFoundException();
        }

        rewardMapper.updateReward(reward);

        updateRewardItems(reward);
    }

    private void updateRewardItems(Reward reward) {

        List<RewardItem> storedItems = rewardMapper.getRewardItemsByRewardId(reward.getId());
        List<RewardItem> updateItems = reward.getItems();

        for (RewardItem item : updateItems) {
            updateOrSaveRewardItem(reward.getId(), item);
        }

        List<RewardItem> removeItems = storedItems.stream().filter(storedItem -> !updateItems.contains(storedItem)).collect(Collectors.toList());

        for (RewardItem removeItem : removeItems) {
            rewardMapper.deleteRewardItem(removeItem.getId());
        }

    }
    private void updateOrSaveRewardItem(Long rewardId, RewardItem item) {

        RewardItem rewardItem = rewardMapper.getRewardItemById(item.getId());
        if (rewardItem == null) {
            item.setRewardId(rewardId);
            rewardMapper.insertRewardItem(item);
            return;
        }

        rewardMapper.updateRewardItem(item);
    }

    public void deleteReward(Long rewardId) {
        Reward reward = rewardMapper.getRewardById(rewardId);
        if (reward == null) {
            throw new RewardNotFoundException();
        }

        rewardMapper.deleteReward(rewardId);
    }
}
