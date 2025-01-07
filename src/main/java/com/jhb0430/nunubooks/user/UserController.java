package com.jhb0430.nunubooks.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nunubooks/account")
@Controller
public class UserController {
	
	@GetMapping("/sign-up")
	public String joinUser() {
		
		return "account/sign-up";
	}
	
	@GetMapping("/login")
	public String loginUser() {
		
		return "account/login";
	}
	
	@GetMapping("/main")
	public String mainPage() {
		
		return "books/main";
	}

	
	

}
