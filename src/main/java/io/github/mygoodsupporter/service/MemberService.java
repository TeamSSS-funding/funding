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

		member.setM_type("ROLE_USER");
		String hashedPassword = passwordEncoder.encode(member.getM_password());
		member.setM_password(hashedPassword);

		return memberDao.insertMember(member);

	}

	public String idCheck(String m_id) {
		Member member = memberDao.getMemberById(m_id);
		String result = "";
		
		if(member == null) {
			result = "ok";
		} else {
			result = "no";
		}
		return result;
	}
}