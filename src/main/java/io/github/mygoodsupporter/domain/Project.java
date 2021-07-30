package io.github.mygoodsupporter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Project {

    private Long id;
    private Long userId;
    private String name;
    private String slug;
    private int targetAmount;
    private int currentAmount;
    private String content;
    private String startDate;
    private String endDate;
    private ProjectStatus status;

    @Builder
    public Project(Long userId, String name, int targetAmount, String content) {
        this.userId = userId;
        this.name = name;
        this.slug = name.replace(' ', '-');
        this.targetAmount = targetAmount;
        this.currentAmount = 0;
        this.content = content;
        this.status = ProjectStatus.FUNDING;
    }

    public void supportProject(int amount) {
        currentAmount += amount;
        if (currentAmount >= targetAmount) {
            status = ProjectStatus.SUCCEED;
        }
    }
}
