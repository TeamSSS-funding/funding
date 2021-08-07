package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public int create(User user) {


		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		user.getAuthorities().add(new Authority(user.getUsername(),"ROLE_USER"));

		int result =  userMapper.insertUser(user);

		for (Authority authority : user.getAuthorities()) {
			userMapper.insertAuthority(authority);
		}

		return result;
	}

	public String idCheck(String id) {
		User user = userMapper.getUserByUsername(id);
		String result = "";
		
		if(user == null) {
			result = "ok";
		} else {
			result = "no";
		}
		return result;
	}

	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

}