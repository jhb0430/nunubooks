package com.jhb0430.nunubooks.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jhb0430.nunubooks.user.domain.User;

@Mapper
public interface UserRepository {

	
	public int addUser(
			@Param("loginId")String loginId
			,@Param("name")String name
			,@Param("email")String email
			,@Param("password")String password
			,@Param("postcode")String postcode
			,@Param("address")String address
			,@Param("phoneNumber")String phoneNumber
			,@Param("point") int point
			);
	
	
	public int selectCountId(
			@Param("loginId")String loginId
			);
		
	public User selectLoginUser(
			@Param("loginId")String loginId
			,@Param("password")String password
			);
	
	
	// 아이디찾기
	public User findUserId(
			@Param("name")String name
			,@Param("email")String email
			,@Param("phoneNumber")String phoneNumber
			);	
	
	//비밀번호 재설정
	public int updatePassword(
			@Param("loginId") String loginId
			,@Param("email") String email
			,@Param("password") String password
			);
	//임시 비밀번호로 변경됨 
	public int updateTmpPassWord(
			@Param("email") String email
			,@Param("password") String password
			);
	
	
	
	// 사용자의 id값 조회
	public User selectUserById(@Param("id") int id);
	
	public int updateUser(@Param("id") int id
			,@Param("point") int point
			);
}
