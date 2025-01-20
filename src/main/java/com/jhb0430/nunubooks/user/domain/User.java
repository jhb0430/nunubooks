package com.jhb0430.nunubooks.user.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`user`")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name="loginId")
	private String  loginId;
	private String  email;
	private String  postcode;
	private String  password;
	private String  address;
	@Column(name="phoneNumber")
	private String  phoneNumber;
	
	private int point;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private LocalDateTime  createdAt;
	@Column(name="updatedAt")
	@CreationTimestamp
	private LocalDateTime  updatedAt;
	
	
}
