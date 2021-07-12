package com.icia.funding.service;

import com.icia.funding.dto.MemberDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {

        MemberDTO member = new MemberDTO();
        member.setM_id("aa123");
        member.setM_password("1234");
        member.setM_name("이름");
        member.setM_phone("010-2944-4442");
        member.setM_email("bbbb1234@email.com");
        member.setM_type("ADMIN");

        int result = memberService.memberJoin(member);

        assertEquals(1, result);
    }
}