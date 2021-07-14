package com.icia.funding.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.icia.funding.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	// 회원가입
	public int memberJoin(MemberDTO member) {
		return sql.insert("membermapper.memberjoin",member);
	}

	// 로그인
	public String login(MemberDTO member) {
		return sql.selectOne("membermapper.memberlogin", member);
	}

	//아이디 체크
	public String idCheck(String m_id) {
		
		return sql.selectOne("membermapper.idcheck", m_id);
	}


	public MemberDTO getMemberByID(String m_id) {
		MemberDTO member = sql.selectOne("getMemberById", m_id);
		return member;
	}
}
