package io.github.mygoodsupporter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class MakerProfile {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String phone;

}
