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
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name="`ordered_book_list`")
@Entity
public class OrderedBookList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="orderId")
	private int orderId;
	
	@Column(name="itemId")
	private int itemId;
	
	private int quantity;
	
	private int price;
	
	
	@CreationTimestamp
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	
}
