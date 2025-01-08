package com.jhb0430.nunubooks.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.user.domain.User;
import com.jhb0430.nunubooks.user.service.UserService;

import jakarta.servlet.http.HttpSession;



@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userSevice;
	

	public UserRestController(UserService userSevice) {
		this.userSevice = userSevice;
	}
	
	// 회원가입 기능
	@PostMapping("/sign-up")
	public Map<String,String> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			,@RequestParam("password") String password
			,@RequestParam("postcode") String postcode
			,@RequestParam("address") String address
			,@RequestParam("phoneNumber") String phoneNumber
			){
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userSevice.addUser(loginId, name, email, password, postcode, address, phoneNumber)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}

	
	
	// 아이디 중복 방지
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(
			@RequestParam("loginId") String loginId
			){
		Map<String, Boolean> resultMap = new HashMap<>();
		
		//if(userSevice.isDuplicateId(userId)) {
			
		//}
		resultMap.put("isDuplicate", userSevice.isDuplicateId(loginId));
		
		return resultMap;
		
	}
	
	
	// 로그인 기능 
	
	@PostMapping("/login")
	public Map<String,String> loginUser(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpSession session 
			){
		
			Map<String,String> resultMap = new HashMap<>();
			
			User user = userSevice.loginUser(loginId, password);
			
			if(user != null) {
				// user id, user name
				session.setAttribute("userId", user.getId());
				session.setAttribute("userLoginId", user.getLoginId());
				session.setAttribute("userName", user.getName());
				
				resultMap.put("result", "success");
				
			} else {
				resultMap.put("result", "fail");
			}
	
			return resultMap;
		
		}
	
	// 아이디 찾기
	@PostMapping("/findId")
	public Map<String,String> findUserId(
			@RequestParam("email") String email
			,@RequestParam("phoneNumber") String phoneNumber
			){
		
		Map<String,String> resultMap = new HashMap<>();
		
		User user = userSevice.findUserId(email, phoneNumber);
		
		if(user != null) {
			
			resultMap.put("result", "success");
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
}
