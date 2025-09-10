package com.jhb0430.nunubooks.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		int point = 3000;
		
		if(userSevice.addUser(loginId, name, email, password, postcode, address, phoneNumber, point)) {
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
				session.setAttribute("userPoint", user.getPoint());
				
				resultMap.put("result", "success");
				
			} else {
				resultMap.put("result", "fail");
			}
	
			return resultMap;
		
		}
	
	// 아이디 찾기
	@PostMapping("/findId")
	public Map<String,String> findUserId(
			@RequestParam("name") String name
			,@RequestParam("userInfo") String userInfo
//			,@RequestParam("email") String email
//			,@RequestParam("phoneNumber") String phoneNumber
			){
		
		
		Map<String,String> resultMap = new HashMap<>();
		
//		User user = userSevice.findUserId(name, email, phoneNumber);
		User user = userSevice.findUserId(name, userInfo);
		
		if(user != null) {
			
			resultMap.put("result", "success");
			resultMap.put("loginId", user.getLoginId()); 
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	// 회원정보 수정 들어가기 
	@PostMapping("/enter-info")
	public Map<String,String> enterUserInfo(
			@RequestParam("password") String password
			,HttpSession session 

			){
		int id = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		User user = userSevice.enterUserInfo(id, password);
		
		if(user != null) {
			resultMap.put("result", "success");
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
/*	
 * 회원정보 전체 수정용으로 서야하는ㄷ수마ㅜㅎ;ㅁ
 * 얘를 써야겠는데???? 
 */
	
	@PutMapping("/update/user")
	public Map<String,String> updateUserInfo(
//			@RequestParam("password") String password
//			,@RequestParam("email") String email
//			,@RequestParam("postcode") String postcode
//			,@RequestParam("address") String address
//			,@RequestParam("phoneNumber") String phoneNumber
			@RequestParam(value = "password", required = false) String password
			,@RequestParam(value = "email", required = false) String email
	        ,@RequestParam(value = "postcode", required = false) String postcode
	        ,@RequestParam(value = "address", required = false) String address
	        ,@RequestParam(value = "phoneNumber", required = false) String phoneNumber
			,HttpSession session
			){
			// 조건 걸어서 입력 안받으면 변경 안되도록...? 
		int id = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		 
	    try {
	        int updateCount = userSevice.updateUserInfo(id, password, email, postcode, address, phoneNumber);
	        
	        if (updateCount > 0) {
	            resultMap.put("result", "success"); // 변경 성공
	        } else {
	            resultMap.put("result", "no_changes"); // 변경된 사항이 없는 경우
	        }
	    } catch (Exception e) {
	        resultMap.put("result", "fail"); // DB 오류 같은 진짜 실패
	    }
		
		return resultMap;
	}
	
/*
	@PutMapping("/update/user")
	public Map<String,String> updatePassword(
			@RequestParam("password") String password
			,HttpSession session
			){
		
		int id = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userSevice.updateUserInfo(id, password) > 0) {
			resultMap.put("result", "success");
			
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
 */	
	
	
}
