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
        member.setM_id("abba123");
        member.setM_password("1234");
        member.setM_name("이름");
        member.setM_phone("010-2944-4442");
        member.setM_email("bbbb1234@email.com");
        member.setM_type("ADMIN");

        int result = memberService.join(member);

        assertEquals(1, result);
    }
}