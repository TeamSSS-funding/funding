package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	User getUserById(Long userId);
	User getUserByUsername(String username);

	void insertUser(User user);
	void deleteUser(long userId);

	void insertAuthority(Authority authority);

}
