package com.icia.funding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icia.funding.dto.MemberDTO;
import com.icia.funding.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService ms;
	
	private ModelAndView mav;
	
	// 회원가입 페이지 요청
	@RequestMapping(value="/memberJoinPage")
	public String memberJoinPage() {
		return "memberJoin";
	}
	
	// 회원가입
	@RequestMapping(value="/memberJoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) {
		mav = ms.memberJoin(member);
		return mav;
	}
	
	// 로그인 페이지 요청
	@RequestMapping(value="/loginPage")
	public String loginPage() {
		return "login";
	}
	
	// 로그인
	@RequestMapping(value="/login")
	public ModelAndView login(@ModelAttribute MemberDTO member) {
		mav = ms.login(member);
		return mav;
	}
	
	// 프로젝트 신청 화면 요청
	@RequestMapping(value="/projectRequestPage")
	public String projectRequestPage() {
		return "projectRequest";
	}

}
