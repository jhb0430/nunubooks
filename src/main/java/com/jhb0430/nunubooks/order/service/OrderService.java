package com.jhb0430.nunubooks.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.service.BookService;
import com.jhb0430.nunubooks.cart.dto.CartDTO;
import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.cart.service.CartService;
import com.jhb0430.nunubooks.order.domain.Order;
import com.jhb0430.nunubooks.order.domain.OrderedBookList;
import com.jhb0430.nunubooks.order.dto.OrderDTO;
import com.jhb0430.nunubooks.order.repository.OrderRepository;
import com.jhb0430.nunubooks.order.repository.OrderedBookListRepositoy;
import com.jhb0430.nunubooks.user.service.UserService;

@Service
public class OrderService {

	// 로그인된 사용자의 장바구니/ 구매 하고자하는 상품 정보를 가져온다
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	private OrderRepository orderRepository;
	private OrderedBookListRepositoy orderedBookListRepositoy;
	
	private CartService cartService;
	private UserService userService;
	private BookService bookService;
	
	public OrderService(OrderRepository orderRepository
			,CartService cartService
			,UserService userService
			,BookService bookService
			,OrderedBookListRepositoy orderedBookListRepositoy
			) {
		this.orderRepository = orderRepository;
		this.cartService = cartService;
		this.userService = userService;
		this.bookService = bookService;
		this.orderedBookListRepositoy = orderedBookListRepositoy;
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
			,int point
			) {
		
		Order order = Order.builder()
				.userId(userId)
				.name(name)
				.phoneNumber(phoneNumber)
				.postcode(postcode)
				.address(address)
				.totalPrice(totalPrice)
				.shippingFee(shippingFee)
				.payments(payments)
				.point(point)
				.build();
		
		if(totalPrice >= 15000 ){
			shippingFee = 0;
		}  else {
			shippingFee = 4000;
		}
		
		
/*
이제 말한대로 주문에 포함된 책 정보들만 ordered_list에 insert하면됩니다
insert할 대상은 cart에 담긴 목록들이고요. 이를 조회 해서
ordered_list 테이블에 항목에 맞게 저장하면 되요!

말한대로 카트도 비우면 되고요.

OrderService에서 addOrder 메소드에서 order를 저장하고 바로 이 작업을 수행하면 됩니다.
즉 해당 메소드에서 해당 코드를 이어서 작성하면 되요!!
 */
		
		
		try {
			
			 orderRepository.save(order);// 주문 저장
			 
			 // 장바구니 항목 ordered boook list에 저장 
			 TotalDTO cart = cartService.getCartList(userId);
			 
			 for(CartDTO cartDTO : cart.getCartDTOList()) {
				 
				 OrderedBookList orderedBookList = OrderedBookList.builder()
						 .orderId(order.getId())
						 .itemId(cartDTO.getItemId())
						 .quantity(cartDTO.getQuantity())
//						 .price(totalPrice)
						 .price(cartDTO.getBookInfo().getItem().get(0).getPriceSales())
						 .build();
				 
				 orderedBookListRepositoy.save(orderedBookList);
			 }
			 
			 userService.updateUserPoint(userId, point); // 포인트 업데이트
			
			 // 장바구니 비우기
			 for (CartDTO cartDTO : cart.getCartDTOList()) {
		            cartService.deleteCart(cartDTO.getId(), userId);
		        }
			 
			 
		return true;
		
		} catch(Exception e) {
			return false;
		}
		
	}
	
	// 주문완료-> 주문번호 생성 
	
	
	
	public OrderDTO getOrderedBookList(int orderId) {
		
		// 주문 조회
		Order order = orderRepository.findById(orderId)
				.orElse(null);
		
		// orderId마다 정보 가져오기 
		List<OrderedBookList> orderedList = orderedBookListRepositoy.findAllByOrderId(orderId);
	
		if (orderedList.isEmpty()) {
			return 
					OrderDTO.builder()
					.orderId(order.getId())
					.userId(order.getUserId())
					.orderedBooks(new ArrayList<>())
					.build();
					
			
		}
		
		TotalDTO cart = cartService.getCartList(order.getUserId());
		
		OrderDTO orderDTO = OrderDTO.builder()
									.orderId(order.getId())
									.userId(order.getUserId())
									.totalDTO(cart)
									.orderedBooks(orderedList)
									.build();
		
		return orderDTO;
	
	}
}
