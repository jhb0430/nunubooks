package com.jhb0430.nunubooks.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jhb0430.nunubooks.books.domain.Data;
import com.jhb0430.nunubooks.books.dto.BookDTO;
import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.order.domain.Order;
import com.jhb0430.nunubooks.order.domain.OrderedBookList;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderDTO {

	
	private int orderId;
	private int cartId;
	private int itemId;
	private int userId;
	
	private int quantity;
	private int price;
	private int totalPrice;
	
	private String orderItemName;
	
	private String impUid;
	
    private String merchantUid;
	
	
	private LocalDateTime createdAt; //주문 시간을 출력?
	
	private Order order;
	
	private TotalDTO totalDTO; // 장바구니를 가져오기 위함
	
	 private List<OrderedBookList> orderedBooks; 
	 
	 private BookDTO book;
	
	 private List<OrderDTO> orderDTOList;
	 private List<BookDTO> books;
	 
	 private List<Data> item;
}
