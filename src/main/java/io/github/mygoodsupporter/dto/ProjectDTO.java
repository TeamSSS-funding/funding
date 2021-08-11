package io.github.mygoodsupporter.dto;

import io.github.mygoodsupporter.domain.project.ProjectStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter @Setter
public class ProjectDTO {
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

    private MultipartFile contentsImageFile;
    private MultipartFile titleImageFile;
}
