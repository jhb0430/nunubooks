package com.jhb0430.nunubooks.cart.service;

import org.springframework.stereotype.Service;

import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.repository.CartRepository;

@Service
public class CartService {

	private CartRepository cartRepository;
	
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	
	public boolean addCart(String itemId, int quantity) {
		
		quantity = 1;
		
		Cart cart = Cart.builder()
				.itemId(itemId)
				.quantity(quantity)
				.build();
		
		try {
			cartRepository.save(cart);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	
}
