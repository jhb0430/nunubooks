package com.jhb0430.nunubooks.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.repository.CartRepository;

@Service
public class CartService {

	private CartRepository cartRepository;
	
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	
	public boolean addCart(String itemId, int quantity, String userId) {
		
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
	
	public List<Cart> cartList(){
		List<Cart> cartList = cartRepository.findAll();
		
		return cartList;
	}
	
// 장바구니 삭제
	
	public boolean deleteCart(int id, String itemId) {
		
		Optional<Cart> optionalCart = cartRepository.findById(id);
		
		if(optionalCart.isPresent()) {
			Cart cart = optionalCart.get();
			
			if(cart.getItemId() == itemId) {
				cartRepository.delete(cart);
			}
			
			return true;
		} else {
			return false;
		}
	}
	

}
