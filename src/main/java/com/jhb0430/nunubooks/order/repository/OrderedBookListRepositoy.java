package com.jhb0430.nunubooks.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.order.domain.Order;
import com.jhb0430.nunubooks.order.domain.OrderedBookList;

public interface OrderedBookListRepositoy extends JpaRepository<OrderedBookList, Integer>{

	// orderId를 입력하면 orderId가 들어있는 전체 쿼리를 보여준다
public List<OrderedBookList> findAllByOrderId(int orderId);
}
