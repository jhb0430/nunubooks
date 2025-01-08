package com.jhb0430.nunubooks.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.cart.domain.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{

	// userId를 입력하면 userId가 들어있는 전체 쿼리를 보여준다
	public List<Cart> findAllByUserIdOrderByIdDesc(int userId);
	
}
