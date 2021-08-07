package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    @Test
    public void createUserTest() {
        //given

        //when

        //then

    }
}