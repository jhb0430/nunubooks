package com.jhb0430.nunubooks.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserRepository {

	
	public int addUser(
			@Param("userId")String userId
			,@Param("email")String email
			,@Param("password")String password
			,@Param("postcode")String postcode
			,@Param("address")String address
			,@Param("phoneNumber")String phoneNumber
			);
	
	
	public int selectCountId(
			@Param("userId")String userId
			);
		
	
}
