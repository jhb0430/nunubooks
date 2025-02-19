package com.jhb0430.nunubooks.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhb0430.nunubooks.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/nunubooks/account")
@Controller
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService =userService;
	}
	
	
	
	@GetMapping("/sign-up")
	public String joinUser() {
		
		return "account/sign-up";
	}
	
	@GetMapping("/login")
	public String loginUser() {
		
		return "account/login";
	}



	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		
		return "redirect:/nunubooks/account/main";
	}
	
	
	@GetMapping("/find-account")
	public String findUser() {
		
		
		return "account/findUser";
	}
	
	@GetMapping("/userInfo")
	public String UserInfo() {
		
		
		return "account/userInfo-pw";
	}
	
	

}
