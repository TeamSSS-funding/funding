package com.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void joinTest() {

        Member member = new Member();
        member.setId("abba123");
        member.setPassword("1234");

        int result = memberService.join(member);

        assertEquals(1, result);
    }
}