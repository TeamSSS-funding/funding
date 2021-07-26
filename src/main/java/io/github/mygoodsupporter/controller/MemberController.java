package io.github.mygoodsupporter.controller;

import io.github.mygoodsupporter.dto.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import io.github.mygoodsupporter.domain.Member;
import io.github.mygoodsupporter.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;



	@GetMapping("/deliveryWritePage")
	public String writePage() {
		return "deiliveryWrite";
	}
}