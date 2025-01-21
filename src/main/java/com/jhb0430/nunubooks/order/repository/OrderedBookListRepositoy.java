package com.jhb0430.nunubooks.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhb0430.nunubooks.order.domain.Order;
import com.jhb0430.nunubooks.order.domain.OrderedBookList;

public interface OrderedBookListRepositoy extends JpaRepository<OrderedBookList, Integer>{

public List<OrderedBookList> findByOrderId(int orderId);
}
