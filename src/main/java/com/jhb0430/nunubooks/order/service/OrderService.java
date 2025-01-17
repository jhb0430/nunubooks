package com.jhb0430.nunubooks.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.cart.service.CartService;
import com.jhb0430.nunubooks.order.dto.OrderDTO;
import com.jhb0430.nunubooks.order.repository.OrderRepository;
import com.jhb0430.nunubooks.user.domain.User;

@Service
public class OrderService {

	// 로그인된 사용자의 장바구니/ 구매 하고자하는 상품 정보를 가져온다
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	private OrderRepository orderRepository;
	private CartService cartService;
	
	public OrderService(OrderRepository orderRepository, CartService cartService) {
		this.orderRepository = orderRepository;
		this.cartService = cartService;
	}
	
	
	public OrderDTO getOrderList(int userId ) {
		
		TotalDTO cart = cartService.getCartList(userId); // 장바구니 리스트 출력
		
		
		OrderDTO orderDTO = OrderDTO.builder()
							.cartId(cart.getCartId())
							.itemId(cart.getItemId())
							.userId(userId)
							.totalDTO(cart)
							.build();
		
//		if(userAddr.contains("제주특별자치도")) {
//			shippingFee = 6000; 도서산간지역이 생각보다 많군...
//		} 배송비 - TotalDTO로 넘김. cart에서도 쓰기 때문...
		
		
		return orderDTO;

		
	}
	
	
	
}
