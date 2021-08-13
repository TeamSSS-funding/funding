package io.github.mygoodsupporter.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void loadMemberTest() {
    }

}