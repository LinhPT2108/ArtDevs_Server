package com.artdevs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginControllerTest {
	
//	@GetMapping("/account/login")
//	@ResponseBody
//	public String getLogin() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getName());
//		return "123123";
//	}
	
	@GetMapping("/login/success")
	@ResponseBody
	public String getLoginsuccess() {
		
		return "logindc ròi dmm";
	}
}
