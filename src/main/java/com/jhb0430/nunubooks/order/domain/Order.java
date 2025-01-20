package com.jhb0430.nunubooks.order.domain;

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
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="`order`")
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	private String name;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	private String postcode;
	
	private String address;
	
	@Column(name="totalPrice")
	private int totalPrice;
	
	@Column(name="shippingFee")
	private int shippingFee;
	
	private String payments;
	
	private int point;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
	
	
}
