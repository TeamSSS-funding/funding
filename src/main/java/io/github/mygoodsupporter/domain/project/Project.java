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

    private String titleImageUrl;
    private String contentsImageUrl;


    @Builder
    public Project(Long userId, Long categoryId, String title, String subtitle, int targetAmount, int currentAmount, LocalDateTime startDate, LocalDateTime endDate, ProjectStatus status, String titleImageUrl, String contentsImageUrl) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.subtitle = subtitle;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.titleImageUrl = titleImageUrl;
        this.contentsImageUrl = contentsImageUrl;
    }

    public static Project createProject(Long userId, Long categoryId, String title) {

        return Project.builder()
                .userId(userId)
                .categoryId(categoryId)
                .title(title)
                .subtitle(" ")
                .targetAmount(0)
                .currentAmount(0)
                .startDate(null)
                .endDate(null)
                .status(ProjectStatus.PREPARING)
                .titleImageUrl("")
                .build();
    }


    public void supportProject(int amount) {
        currentAmount += amount;
        if (currentAmount >= targetAmount) {
            status = ProjectStatus.SUCCEED;
        }
    }
}
