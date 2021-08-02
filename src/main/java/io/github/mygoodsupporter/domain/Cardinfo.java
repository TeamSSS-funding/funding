package io.github.mygoodsupporter.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cardinfo {
    private long id;
    private String card_number;
    private String expired_date;
    private String card_password;
    private String date_of_birth;
    private String userId;


}
