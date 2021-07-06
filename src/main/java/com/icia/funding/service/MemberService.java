package com.icia.funding.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.funding.dao.MemberDAO;
import com.icia.funding.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO mdao;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

	// 회원가입
	public ModelAndView memberJoin(MemberDTO member) {
		mav = new ModelAndView();
		int insertResult = mdao.memberJoin(member);
		if(insertResult > 0) {
			mav.setViewName("login");
		} else {
			mav.setViewName("memberJoin");
		}
		return mav;
	}

	// 로그인
	public ModelAndView login(MemberDTO member) {
		mav = new ModelAndView();
		String loginId = mdao.login(member);
		if(loginId != null) {
			session.setAttribute("loginMember", loginId);
			mav.setViewName("home");
		} else {
			mav.setViewName("login");
		}
		return mav;
	}

}
