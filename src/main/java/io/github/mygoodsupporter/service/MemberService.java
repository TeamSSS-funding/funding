package io.github.mygoodsupporter.service;

import io.github.mygoodsupporter.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.mygoodsupporter.dao.MemberDAO;


@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberDAO memberDao;
	private final PasswordEncoder passwordEncoder;

	public int join(Member member) {

		member.setType("ROLE_USER");
		String hashedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(hashedPassword);

		return memberDao.insertMember(member);

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
}