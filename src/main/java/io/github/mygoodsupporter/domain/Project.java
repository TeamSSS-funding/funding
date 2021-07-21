package io.github.mygoodsupporter.domain;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Project {

    private Long id;
    private String memberId;
    private String name;
    private String slug;
    private int targetAmount;
    private int currentAmount;
    private String content;
    private String startDate;
    private String endDate;
    private ProjectStatus status;

    @Builder
    public Project(String memberId, String name, int targetAmount, String content) {
        this.memberId = memberId;
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
