package io.github.mygoodsupporter.controller;


import io.github.mygoodsupporter.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import io.github.mygoodsupporter.service.MemberService;
import org.springframework.web.servlet.ModelAndView;

import io.github.mygoodsupporter.domain.Member;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	// 마이페이지 화면 요청
	@GetMapping(value="/users/mypage")
	public String mypage() {
		return "users/mypage";
	}

	// 회원정보 수정 화면 요청
	@GetMapping(value="/users/update")
	public String update(@AuthenticationPrincipal MemberDetails memberDetails, @ModelAttribute Member member, Model model) {
		String id = memberDetails.getId();
		member = memberService.update(id);
		model.addAttribute("member",member);
		return "users/update";
	}

	// 회원정보 수정
	@PostMapping(value="/users/updateprocess")
	public String updateprocess(@ModelAttribute Member member) {
		int updateResult = memberService.updateprocess(member);
		if(updateResult > 0) {
			return "users/mypage";
		} else {
			return "users/update";
		}
	}

	// 회원목록 조회
	@GetMapping(value = "/users/list")
	public String memberList(Model model) {
		List<Member> memberList = memberService.memberList();
		model.addAttribute("memberList",memberList);
		return "users/list";
	}
}