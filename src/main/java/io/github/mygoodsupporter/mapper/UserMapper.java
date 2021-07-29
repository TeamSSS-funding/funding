package io.github.mygoodsupporter.mapper;

import io.github.mygoodsupporter.domain.user.Authority;
import io.github.mygoodsupporter.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	User getUserByUsername(String username);

	int insertUser(User user);

	void insertAuthority(Authority authority);

}
