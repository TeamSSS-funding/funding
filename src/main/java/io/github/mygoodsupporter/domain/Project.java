package io.github.mygoodsupporter.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Project {

    private Long id;
    private Long userId;
    private String title;
    private String subtitle;
    private int goalAmount;
    private int currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus status;

    @Builder
    public Project(Long userId, String subtitle) {
        this.userId = userId;
        this.title = "";
        this.subtitle = subtitle;
        this.goalAmount = 0;
        this.currentAmount = 0;
        this.status = ProjectStatus.PREPARING;
    }

    public void supportProject(int amount) {
        currentAmount += amount;
        if (currentAmount >= goalAmount) {
            status = ProjectStatus.SUCCEED;
        }
    }
}
