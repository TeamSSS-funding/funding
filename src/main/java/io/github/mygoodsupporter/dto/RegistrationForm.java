package io.github.mygoodsupporter.dto;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class RegistrationForm {
    private String id;
    private String password;
    private String email;
    private String name;
    private String phone;


    public Member toMember() {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setEmail(email);
        member.setName(name);
        member.setPhone(phone);
        return member;
    }
}
