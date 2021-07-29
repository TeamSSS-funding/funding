package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {

        Member member = new Member();
        member.setId("abba123");
        member.setPassword("1234");

        int result = memberService.create(member);

        assertThat(result).isEqualTo(1);
    }
}