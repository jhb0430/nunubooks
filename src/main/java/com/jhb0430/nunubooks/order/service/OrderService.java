package com.jhb0430.nunubooks.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.domain.Data;
import com.jhb0430.nunubooks.books.dto.BookDTO;
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

import reactor.core.publisher.Mono;

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
	
	
	// 주문번호 1개에 대한 주문 정보 조회
	public OrderDTO getOrderedBookList(int orderId, int userId) {
		
		// 주문 완료 - > orderId, itemId, quantity, price , createdAt
		// orderId가 같은 itemId의 정보를 조회해온다 ?? 
		// itemId를 기준으로 책 정보를 가져오는 건 bookService에 있긴 한데.. .
		// cartService를 가져올 필요는 없음 
		// 하지만 cartService처럼 리스트를 만들어야 할 필요는 있다.
		
		// 주문에 포함된 책들을 리스트로 가져온다...? BookDTO가 List여야하나..? 
		// 근데 Item List만 가져와도 되지않나..? @-@
		// 주문자의 정보 -> Order에 저장된 정보도 가져와야함...
		// bookDTO->item()의 정보.
		
//		List<OrderDTO> orderDTOList = new ArrayList<>();
//		List<BookDTO> books = new ArrayList<>();
		
		
		WebClient webClient = webClientBuilder.build();
		// orderId마다 정보 가져오기 
		List<OrderedBookList> orderedList = orderedBookListRepositoy.findAllByOrderId(orderId);
		List<Data> orderedbookInfo = new ArrayList<>(); // 주문된 책 정보 담기
	
		int quantity = 0;
		
		for(OrderedBookList orderedBook : orderedList) {
			
			 int itemId = orderedBook.getItemId();
			
			 quantity = orderedBook.getQuantity();
			
			  Mono<BookDTO> response = 
						webClient.get()
						.uri(uriBuilder -> uriBuilder
								.scheme("https")
								.host("www.aladin.co.kr")
								.path("/ttb/api/ItemLookUp.aspx")
								.queryParam("ttbkey","ttbleky22241703001")
								.queryParam("itemIdType","itemId")
								.queryParam("itemId",itemId)
								.queryParam("Cover","Mid")
								.queryParam("output","js")
								.queryParam("Version","20131101")
								.build()
								)
						.retrieve()
						.bodyToMono(BookDTO.class);
			  
			  BookDTO book = response.block();
			  
			  // 정보값이 있으면 - 예외처리
			  if (book != null && book.getItem() != null && !book.getItem().isEmpty()) {
				  orderedbookInfo.add(book.getItem().get(0)); // 정보 추가
		        }
			  
			  
		}
		return OrderDTO.builder()
				.orderId(orderId)
				.userId(userId)
//				.itemId(itemId)
				.item(orderedbookInfo)
				.orderedBooks(orderedList)
				.quantity(quantity)
				.build();
		
		
	}
	
	
	// 주문자의 정보 출력
	public Order OrderUserInfo(int orderId) {
		Optional<Order> optionalOrderInfo = orderRepository.findById(orderId);
		
		return optionalOrderInfo.orElse(null);
	}
	
	
	
	// 주문 번호를 출력해주는 페이지의 필요성.
}
