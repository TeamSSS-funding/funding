package io.github.mygoodsupporter.dao;

import io.github.mygoodsupporter.domain.Authority;
import io.github.mygoodsupporter.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {

	int insertMember(Member member);

	Member getMemberById(String m_id);

	int insertAuthority(Authority authority);

	// 회원정보 수정 요청
	Member select(String id);

	// 회원정보 수정
	int update(Member member);

	// 회원목록 조회
	List<Member> list();

}
