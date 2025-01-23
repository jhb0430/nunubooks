package com.jhb0430.nunubooks.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.order.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/order")
@RestController
public class OrderRestController {

	private OrderService orderService;
	
	public OrderRestController(OrderService orderService) {
		
		this.orderService = orderService;
	}
	
	// 주문 db에 추가
	@PostMapping("/order-add")
	public Map<String,String> completeOrder(
			@RequestParam("name") String name
			,@RequestParam("phoneNumber") String phoneNumber
			,@RequestParam("postcode") String postcode
			,@RequestParam("address") String address
			,@RequestParam("totalPrice") int totalPrice
			,@RequestParam("shippingFee") int shippingFee
			,@RequestParam("payments") String payments
			,@RequestParam("point") int point
			,HttpSession session
			){
	
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userId == 0) { //로그인 정보가 없으면
			resultMap.put("result", "fail");
		}
		
		if(orderService.addOrder(userId, name, phoneNumber, postcode, address, totalPrice, shippingFee, payments, point)) {
			resultMap.put("result", "success");
		} else{
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}
	
	
	
	@DeleteMapping("/delete")
	public Map<String,String> deleteOrder(
			@RequestParam("id") int id
			,HttpSession session){
				
		int userId = (Integer)session.getAttribute("userId");
		
Map<String,String> resultMap = new HashMap<>();
		
		if(userId == 0) {
			resultMap.put("result", "fail");
		}
		
		
		if(orderService.deleteOrder(id, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
	}
	
	
	
}
