package com.jhb0430.nunubooks.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.cart.service.CartService;


@RequestMapping("/cart")
@RestController
public class CartRestController {


	private CartService cartService;
	
	public CartRestController(CartService cartService) {
		this.cartService = cartService;
	}
	
	
	@PostMapping("/add")
	public Map<String, String> addCart(
			@RequestParam("itemId") String itemId
			,@RequestParam("userId") String userId
			,@RequestParam("quantity") int quantity
			){
		
		quantity = 1;
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(cartService.addCart(itemId, quantity, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
}
