package com.icia.funding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	
	@GetMapping("/healthy")
	@ResponseBody
	public String healthy() {
		return "200 ok";
	}
	
}
