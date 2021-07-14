package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.dto.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

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
	@PostMapping("/memberJoin")
	public String join(@ModelAttribute RegistrationForm registrationForm) {
		Member member = registrationForm.toMember();
		int insertResult = memberService.join(member);

		if(insertResult>0) {
			return "users/login";
		}
		return "joinfail";
	}
	
	@RequestMapping(value="/idcheck")
	public @ResponseBody String idCheck(@RequestParam("m_id")String m_id) {
		String result = memberService.idCheck(m_id);
		return result;
	}

	@GetMapping("/login")
	public String loginPage() {

		return "users/login";
	}
}