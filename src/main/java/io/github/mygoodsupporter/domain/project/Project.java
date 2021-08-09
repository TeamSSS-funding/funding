package io.github.mygoodsupporter.domain.project;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Project {

    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String subtitle;
    private int targetAmount;
    private int currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProjectStatus status;

    @Builder
    public Project(Long userId, Long categoryId, String subtitle) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = "";
        this.subtitle = subtitle;
        this.targetAmount = 0;
        this.currentAmount = 0;
        this.status = ProjectStatus.PREPARING;
    }

    public void supportProject(int amount) {
        currentAmount += amount;
        if (currentAmount >= targetAmount) {
            status = ProjectStatus.SUCCEED;
        }
    }
}
