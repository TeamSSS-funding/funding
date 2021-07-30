package io.github.mygoodsupporter.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter
public class Authority {

    private String username;
    private String authority;


    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
