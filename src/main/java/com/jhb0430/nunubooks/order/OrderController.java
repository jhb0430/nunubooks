package com.jhb0430.nunubooks.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhb0430.nunubooks.order.dto.OrderDTO;
import com.jhb0430.nunubooks.order.service.OrderService;
import com.jhb0430.nunubooks.user.domain.User;
import com.jhb0430.nunubooks.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/nunubooks/order")
@Controller
public class OrderController {

	private OrderService orderService;
	private UserService userService;
	
	public OrderController(OrderService orderService, UserService userService) {
		this.orderService = orderService;
		this.userService = userService;
	}
	
	
	
	@GetMapping("/order")
	public String bookOrder(
			HttpSession session
			,Model model) {
		

		int userId = (Integer)session.getAttribute("userId");
		
		User user = userService.getUserById(userId);
		
		String[] detailAddress = user.getAddress().split("\\)");
		
		String userAddr = detailAddress[0] + ")";
		String detailAddr = detailAddress[1];
		
		
		model.addAttribute("user",user);
		model.addAttribute("userAddr",userAddr);
		model.addAttribute("detailAddr",detailAddr);
		
		OrderDTO orderDTO = orderService.getOrderList(userId);

		
		model.addAttribute("orderDTO",orderDTO);
		
//		model.addAttribute("item",);
//		orderDTO.getTotalDTO().getCartDTOList().get(0).getBookInfo();
//		orderDTO.getTotalDTO().getCartDTOList().get(0).getQuantity();
		
		
		return "order/order-page";
	}
	
	@GetMapping("/order/complete")
	public String completeOrder() {
		return "order/order-complete";
	}
	
	
	// 주문 내역 페이지
	@GetMapping("/orderHistory")
	public String OrderHistory() {
		return "order/order-list";
	}
	
	
	//주문 상세정보
	@GetMapping("/orderInfo")
	public String UserorderList(
			@RequestParam("orderId") int orderId
			,HttpSession session
			, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		List<OrderDTO> orderedList = orderService.getOrderedBookList(orderId, userId);
		
		
		model.addAttribute("orderedList",orderedList);
		
		return "order/order-info";
	}
	
	
	
}
