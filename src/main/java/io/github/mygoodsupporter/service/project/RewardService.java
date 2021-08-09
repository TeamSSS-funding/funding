package io.github.mygoodsupporter.service.project;

import io.github.mygoodsupporter.domain.project.Reward;
import io.github.mygoodsupporter.mapper.ItemMapper;
import io.github.mygoodsupporter.mapper.RewardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardService {

    private final RewardMapper rewardMapper;
    private final ItemMapper itemMapper;

    public List<Reward> getRewardsByProjectId(Long projectId) {
        return rewardMapper.getRewardsByProjectId(projectId);
    }

    public Reward getRewardById(Long rewardId) {
        return rewardMapper.getRewardById(rewardId);
    }

    @Transactional
    public Long createReward() {
        return null;
    }

    @Transactional
    public void updateReward() {

    }

    @Transactional
    public void deleteReward() {

    }

}
