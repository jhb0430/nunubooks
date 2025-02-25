package com.jhb0430.nunubooks.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;
import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.dto.CartDTO;
import com.jhb0430.nunubooks.cart.dto.TotalDTO;
import com.jhb0430.nunubooks.cart.repository.CartRepository;
import com.jhb0430.nunubooks.user.service.UserService;

import reactor.core.publisher.Mono;

@Service
public class CartService {

	private CartRepository cartRepository;
	private UserService userService;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	
	public CartService(CartRepository cartRepository,UserService userService) {
		this.cartRepository = cartRepository;
		this.userService = userService;
	}
	
	
	public boolean addCart(int itemId, int quantity, int userId) {
		
		quantity = 1;
		
		Cart cart = Cart.builder()
				.itemId(itemId)
				.userId(userId)
				.quantity(quantity)
				.build();
		
		try {
			
			cartRepository.save(cart);
				
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	
	
	// 장바구니 리스트 출력
	// 로그인 기반으로, 로그인 했을때만 수행되도록 1차 정리 후, 비로그인시에도 저장되도록 수정하기
	// userId를 넣으면 -> 그 사람의 장바구니 목록을 보여준다 .
	public TotalDTO getCartList(int userId
//			public List<CartDTO> getCartList(int userId
//			,HttpSession session
			){
//		int userId = (Integer)session.getAttribute("userId");
		
		WebClient webClient = webClientBuilder.build();
		
		List<Cart> cartList = cartRepository.findAllByUserIdOrderByIdDesc(userId);
// 리스트를 반복하면서 itemId마다 정보 가져오게 됨.... 
		
		List<CartDTO> cartDTOList = new ArrayList<>();
		
        int totalPrice = 0;
        int totalPoints = 0;
        int totalDiscount = 0;
        int totalStandard = 0;
        int shippingFee = 0;
        int finalPrice = 0;
        int cartCount = 0;
		for(Cart cart : cartList) {
			
			int itemId = cart.getItemId();
			
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

//		  return response.block();
		  BookDTO book = response.block();
		  

//		  private int discount = ( (priceStandard - priceSales) / priceStandard) * 100;
		 
		  int quantity = cart.getQuantity();
		  
		  cartCount = cartRepository.countByUserId(userId);
		  
		  int priceSales  =  book.getItem().get(0).getPriceSales();
		  int mileage = book.getItem().get(0).getMileage();
		  
		  int priceStandard =  book.getItem().get(0).getPriceStandard();
		  
		  totalPrice += priceSales * quantity;
		  totalPoints += mileage * quantity;
		  
		  totalStandard += priceStandard * quantity;
		  
		  totalDiscount = totalStandard - totalPrice;
		  
		  
		  if(totalPrice >= 15000 ){
				shippingFee = 0;
			}  else {
				shippingFee = 4000;
			}
		  finalPrice = totalPrice + shippingFee;
		  
		  CartDTO cartDTO = CartDTO.builder()
				  					.id(cart.getId())
				  					.itemId(itemId)
				  					.userId(userId)
				  					.quantity(cart.getQuantity())
				  					.createdAt(cart.getCreatedAt())
				  					.bookInfo(book)
				  					.build();
		  cartDTOList.add(cartDTO);

		}

		TotalDTO totalDTO = TotalDTO.builder()
				.cartId(cartDTOList.get(0).getId())
				.itemId(cartDTOList.get(0).getItemId())
				.userId(userId)
				.cartDTOList(cartDTOList)
				.cartCount(cartCount)
				.totalPrice(totalPrice)
				.totalPoints(totalPoints)
				.totalDiscount(totalDiscount)
				.shippingFee(shippingFee)
				.finalPrice(finalPrice)
				.build();
	
//		return cartDTOList;
		return totalDTO; // 카트가 비워져있을때의 처리.... +++
//		return bookList;
		
		
		/// 배송비는 지역에 따라 구분 할 수 있도록. 
	}
	
	
	
	// 장바구니에 들어있는 갯수 정보 
	public int countCart(int userId) {
		
		return cartRepository.countByUserId(userId);
	}
	
	

		
	
// 장바구니 삭제
	// userId가 따라와주는게 좋다 
	public boolean deleteCart(int id, int userId) {
		
		Optional<Cart> optionalCart = cartRepository.findById(id);
		
		if(optionalCart.isPresent()) {
			Cart cart = optionalCart.get();
			
			if(cart.getUserId() == userId) {
				cartRepository.delete(cart);
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	
//	public int totalPrice() {}
	
	
	// 수량 추가, 제거
		// cart의 id를 전달 받고 수량을 수정.
	public Cart updateQuantity(int id,int quantity) {
		
		Optional<Cart> optionalCart = cartRepository.findById(id);
		
		Cart cart = optionalCart.orElse(null);
		
		if(cart != null) { // cart에 값이 있으면 
			// cart에 값이 있고 quantity가 1 밑으로 내려가지 못하게, 내려가면 db 삭제되어야함.
			if(quantity > 0) {
				
			cart = cart.toBuilder().quantity(quantity).build();
			cart = cartRepository.save(cart);
			} else { // 0이상이 아니면 
				cartRepository.delete(cart);
			}
		}
		return cart;
		
	}

}
