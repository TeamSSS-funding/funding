package io.github.mygoodsupporter.domain.project;

import lombok.*;

@EqualsAndHashCode(of={"title"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Item {
    private Long id;
    private Long projectId;
    private String title;

    @Builder
    protected Item(String title, Long projectId) {
        this.title = title;
        this.projectId = projectId;
    }
}
