package io.github.mygoodsupporter.security;

import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserMapper  userMapper;

    public UserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username: " + username + "notfound");
        }
        return new UserDetails(user);
    }
}

