package com.jhb0430.nunubooks.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{


	
//	public findAllByUserIdOrderByIdDesc(int userId);
	//SELECT `id` FROM `order` WHERE `userId`="#";
	public List<Order> findByUserId(int userId);
	
	public Order findByMerchantUid(String merchantUid);

}
