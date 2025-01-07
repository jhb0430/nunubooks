package com.jhb0430.nunubooks.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.cart.domain.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{

	// 장바구니 저장기능 -> save
	
}
