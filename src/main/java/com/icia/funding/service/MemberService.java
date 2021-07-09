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
	private MemberDAO memberDao;
	
	private ModelAndView modelandview;
	
	@Autowired
	private HttpSession session;

	// 회원가입
	public ModelAndView memberJoin(MemberDTO member) {
		modelandview = new ModelAndView();
		
		int insertResult =0;
		insertResult = memberDao.memberJoin(member);
		if(insertResult>0) {
			modelandview.setViewName("login");
		} else {
			modelandview.setViewName("joinfail");
		}
		return modelandview;
	}

	// 로그인
	public ModelAndView login(MemberDTO member) {
		modelandview = new ModelAndView();
		String loginId = memberDao.login(member);
		if(loginId != null) {
			session.setAttribute("loginMember", loginId);
			modelandview.setViewName("home");
		} else {
			modelandview.setViewName("login");
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