package com.jhb0430.nunubooks.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.user.service.UserService;

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
			@RequestParam("userId") String id
			,@RequestParam("email") String email
			,@RequestParam("password") String password
			,@RequestParam("address") String address
			,@RequestParam("phoneNumber") String phoneNumber
			){
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userSevice.addUser(id, email, password, address, phoneNumber)) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}

}
