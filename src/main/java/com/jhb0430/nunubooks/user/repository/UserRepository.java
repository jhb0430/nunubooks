package com.jhb0430.nunubooks.user.repository;

import org.apache.ibatis.annotations.Param;

public interface UserRepository {

	
	public int addUser(
			@Param("userId")String userId
			,@Param("email")String email
			,@Param("password")String password
			,@Param("address")String address
			,@Param("phoneNumber")String phoneNumber
			);
}
