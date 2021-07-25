package io.github.mygoodsupporter.dto;

import io.github.mygoodsupporter.domain.Address;
import io.github.mygoodsupporter.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter @Setter
public class RegistrationForm {

    @Pattern(regexp = "/^(?=.*[a-z])(?=.*\\d)[a-z\\d]{6,10}$/", message = "아이디는 6~10자리,소문자,숫자를 포함하여 만들어주세요.")
    private String id;
    @Pattern(regexp = "/^(?=.*[a-z])(?=.*\\d)(?=.*[!#$-])[a-zA-Z\\d!#$-]{8,16}$", message = "비밀번호는 8~16자리, 특수문자(!#$-),숫자를 포함하여 만들어주세요.")
    private String password;
    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식을 맞춰서 적어 주세요.")
    private String email;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @Pattern(regexp = "/^\\d{3}-\\d{4}-\\d{4}$/", message = "010-1111-1111 형식으로 입력해주세요.")
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
