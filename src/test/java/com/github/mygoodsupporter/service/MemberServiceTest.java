package com.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:../../../../../../main/webapp/WEB-INF/spring/security-context.xml"})
//@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {

        Member member = new Member();
        member.setId("abba123");
        member.setPassword("1234");

        member.setEmail("bbbb1234@email.com");
        member.setType("ADMIN");

        int result = memberService.join(member);

        assertEquals(1, result);
    }
}