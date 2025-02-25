package com.jhb0430.nunubooks.cart.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import com.jhb0430.nunubooks.books.dto.BookDTO;

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

@Builder(toBuilder=true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name="`cart`")
@Getter
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="itemId")
	private int itemId;
	
	@Column(name="userId")
	private int userId;
	
	private int quantity;
	
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	

}
