package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.member.Authority;
import io.github.mygoodsupporter.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {

	int insertMember(Member member);

	Member getMemberById(String m_id);

	int insertAuthority(Authority authority);


}
