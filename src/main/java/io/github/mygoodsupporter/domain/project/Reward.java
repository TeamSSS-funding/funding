package io.github.mygoodsupporter.domain.project;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Reward {

    private Long id;
    private Long projectId;
    private String title;
    private String description;
    private int amount;
    private List<RewardItem> items = new ArrayList<>();

    @Builder
    public Reward(Long projectId, String title, String description, int amount) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.amount = amount;
    }

    public void addItem(Item item, int quantity) {

        for (RewardItem rewardItem : items) {
            if (rewardItem.getItem().equals(item)) {

            }
        }
        
        RewardItem rewardItem = new RewardItem(item, quantity);
        items.add(rewardItem);
    }

    public void deleteItem(Item item, int quantity) {
        
    }
}
