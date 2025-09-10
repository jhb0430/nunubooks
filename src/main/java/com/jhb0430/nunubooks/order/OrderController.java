package com.jhb0430.nunubooks.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhb0430.nunubooks.order.domain.Order;
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
	public String completeOrder(
			@RequestParam("merchantUid") String merchantUid
//			,HttpSession session
			, Model model
			) {
//		int userId = (Integer)session.getAttribute("userId");
//		List<Order> order = orderService.findOrderId(userId);
		
//		model.addAttribute("order",order);
		model.addAttribute("merchantUid", merchantUid);
		
		return "order/order-complete";
	}
	
	
	// 주문 내역 페이지
	@GetMapping("/orderHistory")
	public String OrderHistory(
			HttpSession session
			, Model model
			) {
		int userId = (Integer)session.getAttribute("userId");
		
//		User user = userService.getUserById(userId);
		// 주문일 // 주문번호 -> orderDTO의 orderId, Order의 createdAt
		// 책 정보 상위 1개 // 총 주문객 주문상태 item[0].title 외- 권
		List<Order> order = orderService.findOrderId(userId);
		
//		int orderId= order.get(0).getId();
		List<OrderDTO> orderInfoList = new ArrayList<>(); // dto 정보 가져와야함
		
		for(Order orderInfo : order) {
			OrderDTO orderList =orderService.getOrderedBookList(orderInfo.getId(), userId);
			orderInfoList.add(orderList);
		}
		
		model.addAttribute("orderInfo",orderInfoList); //그대로 쓰니까 orderId를 tbody에서는 못받아오네... list 돌려야만
		model.addAttribute("order",order);
//		model.addAttribute("user",user);
		
		return "order/order-history";
	}
	
	
	//주문 상세정보
	@GetMapping("/orderInfo")
	public String UserorderList(
			@RequestParam("merchantUid") String merchantUid
//			@RequestParam("orderId") int orderId
			,HttpSession session
			, Model model
			) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		
		int orderId = orderService.findByMerchantUid(merchantUid).getId();
		
		OrderDTO orderInfo = orderService.getOrderedBookList(orderId, userId);
		
//		Order orderUserInfo = orderService.getOrderUserInfo(orderId,userId);
		
		
		
		model.addAttribute("merchantUid",merchantUid);
		model.addAttribute("orderInfo",orderInfo);
		model.addAttribute("orderUser",orderInfo.getOrder());
		
		return "order/order-info";
	}
	
	
	
}
