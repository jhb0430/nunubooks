package com.jhb0430.nunubooks.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.domain.Data;
import com.jhb0430.nunubooks.books.dto.BookDTO;
import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.repository.CartRepository;
import com.jhb0430.nunubooks.user.service.UserService;

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
	public List<Cart> getCartList(int userId){
		List<Cart> cartList = cartRepository.findAllByUserIdOrderByIdDesc(userId);
		
//		Data item = item.builder().title()
		
		return cartList;
	}
	
	
	
	
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
	

}
