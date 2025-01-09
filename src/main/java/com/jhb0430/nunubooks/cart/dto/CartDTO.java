package com.jhb0430.nunubooks.cart.dto;

import java.time.LocalDateTime;

import com.jhb0430.nunubooks.books.dto.BookDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CartDTO {

	private int id;
	private int itemId;
	private int userId;
	private int quantity;
	private LocalDateTime createdAt;

	 private BookDTO bookInfo;
}
