package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.dao.MemberDAO;
import io.github.mygoodsupporter.domain.member.Authority;
import io.github.mygoodsupporter.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDAO memberDao;
	private final PasswordEncoder passwordEncoder;



	public int create(Member member) {


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

	public Member getMemberById(String id) {
		return memberDao.getMemberById(id);
	}

}