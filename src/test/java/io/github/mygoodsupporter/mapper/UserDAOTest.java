package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserDAOTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void loadMemberTest() {
        User user = userMapper.getUserByUsername("nnn");
    }

}