package com.jhb0430.nunubooks.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nunubooks/order")
@Controller
public class OrderController {

	@GetMapping("order")
	public String bookOrder() {
		return "order/order-page";
	}
	
	
	
	
}
