package io.github.mygoodsupporter.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter @Setter
public class Authority {
    private String memberId;
    private String authority;


    public Authority(String memberId, String authority) {
        this.memberId = memberId;
        this.authority = authority;
    }
}
