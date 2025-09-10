package com.jhb0430.nunubooks.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.cart.domain.Cart;
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
			@RequestParam("itemId") int itemId
			,@RequestParam("quantity") int quantity
			,HttpSession session
			){
		
		quantity = 1;
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userId == 0) { //로그인 정보가 없으면
			resultMap.put("result", "fail");
		}
		
//		if(cartService.addCart(itemId, quantity, userId)) {
		if(cartService.insertCart(itemId, quantity, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	
	// 삭제기능 
	@DeleteMapping("/delete")
	public Map<String, String> deleteCart(
			@RequestParam("id") int id
			,HttpSession session
			){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userId == 0) {
			resultMap.put("result", "fail");
		}
		
		
		if(cartService.deleteCart(id, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
	}
	

//		@GetMapping("/update")
		@PutMapping("/update")
		public Cart updateQuantity(@RequestParam("id") int id, @RequestParam("quantity") int quantity) {
			// qauntity 수정 
		// +버튼을 누르면 +1; -를 누르면 -1
			// quantity는 0 이하로 내려갈 수 없음 
			Cart cartQuantity = cartService.updateQuantity(id, quantity);
			
			return cartQuantity;
		}
	
}
