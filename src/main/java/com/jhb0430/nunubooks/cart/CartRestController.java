package com.jhb0430.nunubooks.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.cart.service.CartService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/cart")
@RestController
public class CartRestController {


	private CartService cartService;

	
	public CartRestController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// 로그인 해야만 추가할 수 있게 
	@PostMapping("/add")
	public Map<String, String> addCart(
			@RequestParam("itemId") String itemId
			,@RequestParam("quantity") int quantity
			,HttpSession session
			){
		
		quantity = 1;
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(cartService.addCart(itemId, quantity, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
	// 삭제기능 
	@DeleteMapping("/delete")
	public Map<String, String> deleteCart(
			@RequestParam("itemId") String itemId
			){
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(cartService.deleteCart(0, itemId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
	}
	
}
