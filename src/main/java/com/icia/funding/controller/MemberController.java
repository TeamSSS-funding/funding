package com.icia.funding.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.icia.funding.dto.MemberDTO;
import com.icia.funding.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	private ModelAndView modelandview;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value ="/")
	public String home() {
		return "home";
	}
	// 회원가입 페이지 이동
	@RequestMapping(value="/memberJoinPage")
	public String memberJoinPage() {
		return "memberJoin";
	}
	
	// 회원가입 실행
	@RequestMapping(value="/memberJoin",method= RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) {
		modelandview = new ModelAndView();
		int insertResult = memberservice.memberJoin(member);
		if(insertResult>0) {
			modelandview.setViewName("users/login");
		} else {
			modelandview.setViewName("joinfail");
		}
		return modelandview;
	}
	
	//아이디중복체크
			@RequestMapping(value="/idcheck")
			public @ResponseBody String idCheck(@RequestParam("m_id")String m_id) {
				System.out.println("idcheck 메소드 호출된거 확인하기");
				System.out.println("입력id값"+m_id);
				String result=memberservice.idCheck(m_id);
				return result;
		}
			
	

	@GetMapping("/login")
	public String loginPage() {
		return "users/login";
	}


//
//	// 로그인 처리
//	@RequestMapping(value="/login")
//	public ModelAndView login(@ModelAttribute MemberDTO member) {
//		modelandview = memberservice.login(member);
//		return modelandview;
//	}


	@RequestMapping(value="/member",method= RequestMethod.GET)
	public void doMember(){

	}

	@RequestMapping(value="/admin",method= RequestMethod.GET)
	public void doAdmin(){

	}

	
	// 프로젝트 신청 화면 요청
	@RequestMapping(value="/projectRequestPage")
	public String projectRequestPage() {
		return "projectRequest";
	}


}