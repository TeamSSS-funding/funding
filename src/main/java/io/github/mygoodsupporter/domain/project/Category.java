package io.github.mygoodsupporter.domain.project;

import lombok.*;

@EqualsAndHashCode(of={"name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Category {
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
