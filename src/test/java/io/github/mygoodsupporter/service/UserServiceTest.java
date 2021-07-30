package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {

        User user = new User();
        user.setUsername("abba123");
        user.setPassword("1234");

        int result = memberService.create(user);

        assertThat(result).isEqualTo(1);
    }
}