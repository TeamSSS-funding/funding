package io.github.mygoodsupporter.service.project;

import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.mapper.ItemMapper;
import io.github.mygoodsupporter.mapper.RewardMapper;
import io.github.mygoodsupporter.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardService {

    private final RewardRepository rewardRepository;
    private final RewardMapper rewardMapper;
    private final ItemMapper itemMapper;

    public List<Reward> getRewardsByProjectId(Long projectId) {
        return rewardMapper.getRewardsByProjectId(projectId);
    }

    public Reward getRewardById(Long rewardId) {
        return rewardMapper.getRewardById(rewardId);
    }

    @Transactional
    public Long createReward(Reward reward) {
        rewardRepository.saveReward(reward);
        return reward.getId();
    }

    @Transactional
    public void updateReward(Reward reward) {
        rewardRepository.updateReward(reward);
    }

    @Transactional
    public void deleteReward(Long rewardId) {
        rewardRepository.deleteReward(rewardId);
    }

}
