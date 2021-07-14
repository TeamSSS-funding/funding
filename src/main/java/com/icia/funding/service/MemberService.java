package com.icia.funding.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.funding.dao.MemberDAO;
import com.icia.funding.dto.MemberDTO;

import java.lang.reflect.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private ModelAndView modelandview;
	
	private HttpSession session;

	// 회원가입
	public int memberJoin(MemberDTO member) {
		modelandview = new ModelAndView();
		
		member.setM_type("ROLE_USER");

		String endcodedPassword = passwordEncoder.encode(member.getM_password());

		member.setM_password(endcodedPassword);
        int insertResult = memberDao.memberJoin(member);

		return insertResult;
	}

	// 로그인
	public ModelAndView login(MemberDTO member) {
		modelandview = new ModelAndView();
		String loginId = memberDao.login(member);
		if(loginId != null) {
			session.setAttribute("loginMember", loginId);
			modelandview.setViewName("home");
		} else {
			modelandview.setViewName("users/login");
		}
		return modelandview;
	}
	
	//아이디 중복체크
	public String idCheck(String m_id) {
		String checkResult = memberDao.idCheck(m_id);
		String result = "";
		
		if(checkResult == null) {
			result = "ok";
			
		} else {
			result = "no";
		}
		System.out.println(result);
		return result;
	}



}