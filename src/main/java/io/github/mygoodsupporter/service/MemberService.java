package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Authority;
import io.github.mygoodsupporter.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.mygoodsupporter.dao.MemberDAO;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDAO memberDao;
	private final PasswordEncoder passwordEncoder;

	public int join(Member member) {


		String hashedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(hashedPassword);

		member.getAuthorities().add(new Authority(member.getId(),"ROLE_USER"));

		int result =  memberDao.insertMember(member);

		for (Authority authority : member.getAuthorities()) {
			memberDao.insertAuthority(authority);
		}

		return result;
	}

	public String idCheck(String id) {
		Member member = memberDao.getMemberById(id);
		String result = "";
		
		if(member == null) {
			result = "ok";
		} else {
			result = "no";
		}
		return result;
	}

	// 회원정보 수정요청
	public Member update(String id) {
		return memberDao.select(id);
	}

	// 회원정보 수정
	public int updateprocess(Member member) {
		return memberDao.update(member);
	}

	// 회원목록 조회
	public List<Member> memberList() {
		return memberDao.list();
	}

//	public Member getMemberById(String id) {
//		return memberDao.getMemberById(id);
//	}




}