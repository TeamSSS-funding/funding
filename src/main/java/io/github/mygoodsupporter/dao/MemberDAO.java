package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Member;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

	private final SqlSessionTemplate sql;

	public int insertMember(Member member) {
		return sql.insert("MemberMapper.insertMember",member);
	}

	public Member getMemberById(String m_id) {
		return sql.selectOne("getMemberById", m_id);
	}
}
