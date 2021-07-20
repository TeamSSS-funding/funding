package com.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Transactional
public class MemberDAOTest {

    @Autowired
    MemberDAO memberDAO;

    @Test
    public void loadMemberTest() {
        Member member = memberDAO.getMemberById("nnn");


    }

}