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
			);
	
	
	public int selectCountId(
			@Param("loginId")String loginId
			);
		
	public User selectLoginUser(
			@Param("loginId")String loginId
			,@Param("password")String password
			);
	
	
	public User findUserId(
			@Param("email")String email
			,@Param("phoneNumber")String phoneNumber
			);	
	
	// 사용자의 id값 조회
	public User selectUserById(@Param("id") int id);
}
