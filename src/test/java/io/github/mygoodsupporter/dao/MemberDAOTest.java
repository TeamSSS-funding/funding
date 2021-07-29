package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberDAOTest {

    @Autowired
    MemberDAO memberDAO;

    @Test
    public void loadMemberTest() {
        Member member = memberDAO.getMemberById("nnn");
    }

}