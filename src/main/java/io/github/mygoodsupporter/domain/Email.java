package io.github.mygoodsupporter.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class Email {

    private String emailId;
    private String domain;

    public Email(String emailId, String domain) {
        this.emailId = emailId;
        this.domain = domain;
    }
}


