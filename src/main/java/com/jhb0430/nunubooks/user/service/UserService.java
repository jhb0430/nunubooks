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
			String email
			,String phoneNumber
			) {
		
		User user = userRepository.findUserId(email, phoneNumber);
		
		return user;
	}
	
	// 아이디 값 조회
	public User getUserById(int id) {
		return userRepository.selectUserById(id);
	}
	
	public int updateUserPoint(int id, int point) {
		return userRepository.updateUser(id, point);
	}
	
}
