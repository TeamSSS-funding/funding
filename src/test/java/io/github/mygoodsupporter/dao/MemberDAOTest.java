package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@Transactional
public class MemberDAOTest {

    @Autowired
    MemberDAO memberDAO;

    @Test
    public void loadMemberTest() {
        Member member = memberDAO.getMemberById("nnn");


    }

}