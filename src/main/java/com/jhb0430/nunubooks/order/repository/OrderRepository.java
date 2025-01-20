package com.jhb0430.nunubooks.order.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	
//	public findAllByUserIdOrderByIdDesc(int userId);

}
