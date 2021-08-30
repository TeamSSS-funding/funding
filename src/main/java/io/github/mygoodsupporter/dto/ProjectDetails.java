package io.github.mygoodsupporter.dto;

import io.github.mygoodsupporter.domain.project.Category;
import io.github.mygoodsupporter.domain.project.ProjectStatus;
import io.github.mygoodsupporter.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class ProjectDetails {

    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String subtitle;
    private int targetAmount;
    private int currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;

    private String titleImageUrl;
    private String contentsImageUrl;

    private Category category;
    private MakerProfile makerProfile;

}
