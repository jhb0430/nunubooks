package com.jhb0430.nunubooks.user.service;


import org.springframework.stereotype.Service;

import com.jhb0430.nunubooks.common.SHA256HashingEncoder;
import com.jhb0430.nunubooks.user.domain.User;
import com.jhb0430.nunubooks.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 회원가입 기능 
	// 가입됐으면 true 아니면 false
	public boolean addUser(
			String loginId
			,String name
			,String email
			,String password
			,String postcode
			,String address
			,String phoneNumber
			,int point
			) {
		// 암호화 해줘야함
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		
		int count = userRepository.addUser(loginId, name, email, encodingPassword, postcode, address, phoneNumber, point);
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// 중복 확인 - 입력받은 아이디가 데이터에 존재하는지 안하는지 
	public boolean isDuplicateId(
			String loginId
			) {
		
		int count = userRepository.selectCountId(loginId);
		
		if(count > 0){
			return true;
		 } else {
			return false;
		 }
			
	}
	
	
	// 로그인 기능
	
	public User loginUser(
			String loginId
			,String password
			) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		User user = userRepository.selectLoginUser(loginId, encodingPassword);
		
		return user;
	}
	
	// 아이디 찿기 
	public User findUserId(
			String name
			,String userInfo
//			,String email
//			,String phoneNumber
			) {
		
		String email;
		String phoneNumber;
		
		if (userInfo.contains("@")) {
			email = userInfo;
			phoneNumber = null;
		} else {
			phoneNumber = userInfo;
			email = null;
		}
		
		
		User user = userRepository.findUserId(name,email, phoneNumber);
		
		return user;
	}
	// 임시비밀번호 발급을 위한 정보 
	public boolean findUserforSetPw(
			String name
			,String loginId
			,String email
			) {
		
		int count = userRepository.findUserforSetPw(name, loginId, email);
		if(count > 0){
			return true;
		 } else {
			return false;
		 }
		
	}
	/*
	public int updateUserPassword(
			String loginId
			,String email
			,String password
			) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		return userRepository.updatePassword(loginId, email, encodingPassword);
		
	}
	*/
	
	public int updateTmpPassWord(
			String email
			,String password
			) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		return userRepository.updateTmpPassWord(email, encodingPassword);
//		return userRepository.updateTmpPassWord(email, password);
		
	}
	
	//회원정보 수정 들어가는 페이지.. boolean으로 줘야하나 ?? 일치하는 정보가 있으면->?? 
	public User enterUserInfo(
			int id
			,String password
			) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		User user = userRepository.enterUserInfo(id, encodingPassword);
		
		return user;
	}
	
	
	
//	public int updateUserInfo(int id
//	, String password
//	, String email
//	, Sting postcode
//	, String adderss
//	, String phoneNumber) {
//		String encodingPassword = SHA256HashingEncoder.encode(password);
//		return userRepository.updateUserInfo(id, encodingPassword , email, postcode, address, phoneNumber);
//	}
//	
	public int updateUserInfo(int id, String password) {
		String encodingPassword = SHA256HashingEncoder.encode(password);
		return userRepository.updateUserInfo(id, encodingPassword);
	}
	
	
	// 아이디 값 조회
	public User getUserById(int id) {
		return userRepository.selectUserById(id);
	}
	
	public int updateUserPoint(int id, int point) {
		return userRepository.updateUser(id, point);
	}
	
}
