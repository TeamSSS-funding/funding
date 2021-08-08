package io.github.mygoodsupporter.domain.project;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class RewardItem {
    private Long id;
    private Long rewardId;
    private Item item;
    private Integer quantity;

    @Builder
    public RewardItem(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
