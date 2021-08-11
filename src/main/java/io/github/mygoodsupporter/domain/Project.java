package io.github.mygoodsupporter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

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

    private String titleImageName;
    private String contentsImageName;

    private MultipartFile contentsImage;
    private MultipartFile titleImage;



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



    public void Project(Long id, Long userId,String name,String content,String startDate,String endDate, String titleImageName, String contentsImageName, MultipartFile titleImage, MultipartFile contentsImage) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
        this.titleImageName = titleImageName;
        this.contentsImageName = contentsImageName;
        this.titleImage = titleImage;
        this.contentsImage = contentsImage;
    }
}
