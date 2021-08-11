package io.github.mygoodsupporter.domain.project;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Project(Long userId, Long categoryId, String title) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.subtitle = "";
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
