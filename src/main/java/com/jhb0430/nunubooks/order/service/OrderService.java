package com.jhb0430.nunubooks.order.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.cart.service.CartService;
import com.jhb0430.nunubooks.order.domain.Order;
import com.jhb0430.nunubooks.order.dto.OrderDTO;
import com.jhb0430.nunubooks.order.repository.OrderRepository;
import com.jhb0430.nunubooks.user.domain.User;
import com.jhb0430.nunubooks.user.service.UserService;

@Service
public class OrderService {

	// 로그인된 사용자의 장바구니/ 구매 하고자하는 상품 정보를 가져온다
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	private OrderRepository orderRepository;
	
	private CartService cartService;
	private UserService userService;
	
	public OrderService(OrderRepository orderRepository
			,CartService cartService
			,UserService userService
			) {
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.userService = userService;
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
		
		
		// 주문상품 갯수 => totalDTO-cartDTO
		// 주문 상품 정보 -> cartDTO.bookInfo
		
		return orderDTO;
		
	}

		
	public boolean addOrder(
			int userId
			,String name
			,String phoneNumber
			,String postcode
			,String address
			,int totalPrice
			,int shippingFee
			,String payments
			) {
		
		Order order = Order.builder()
				.userId(userId)
				.name(name)
				.phoneNumber(phoneNumber)
				.postcode(postcode)
				.address(address)
				.totalPrice(totalPrice)
				.shippingFee(shippingFee)
				.build();
		
		if(totalPrice >= 15000 ){
			shippingFee = 0;
		}  else {
			shippingFee = 4000;
		}
		
		
		try {
		 orderRepository.save(order);
		
		return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	// 주문완료-> 주문번호 생성 
	
	
	
	
}
