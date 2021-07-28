package io.github.mygoodsupporter.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.security.MemberDetails;
import io.github.mygoodsupporter.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/test")
	public String testPage(@AuthenticationPrincipal MemberDetails memberDetails, Model model) {

		Member member = memberService.getMemberById(memberDetails.getId());
		model.addAttribute("member", member);

		return "test";
	}
}
