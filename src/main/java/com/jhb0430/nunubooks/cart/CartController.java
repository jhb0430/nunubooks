package com.jhb0430.nunubooks.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nunubooks")
@Controller
public class CartController {

	@GetMapping("/cart")
	public String cartList() {
		
		return "order/cart";
	}
	
}
