package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import io.github.mygoodsupporter.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public User getUserById(Long userId) {
		return userMapper.getUserById(userId);
	}

	public User getUserByUsername(String username) {
		return userMapper.getUserByUsername(username);
	}

	public boolean isDuplicatedUsername(String username) {
		User user = userMapper.getUserByUsername(username);
		return user != null;
	}

	@Transactional
	public void createUser(User user) {

		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		user.getAuthorities().add(new Authority(user.getUsername(),"ROLE_USER"));

		userMapper.insertUser(user);

		for (Authority authority : user.getAuthorities()) {
			userMapper.insertAuthority(authority);
		}
	}

	@Transactional
	public void deleteUser(Long userId) {
		User user = userMapper.getUserById(userId);

		if (user == null) {
			throw new IllegalArgumentException();
		}

		userMapper.deleteUser(userId);
	}


}