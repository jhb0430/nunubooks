package com.jhb0430.nunubooks.order.dto;

import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.order.domain.Order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderDTO {

	
	private int orderId;
	private int cartId;
	private int itemId;
	private int userId;
	
	private Order order;
	
	private TotalDTO totalDTO;
	
}
