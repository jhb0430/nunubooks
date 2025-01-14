package com.jhb0430.nunubooks.cart.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TotalDTO {

	private int cartId;
	private int itemId;
	private int userId;
	
	private List<CartDTO> cartDTOList;
	
	private int totalPrice;
	private int totalPoints;
	private int totalStandard;
	private int totalDiscount;
}
