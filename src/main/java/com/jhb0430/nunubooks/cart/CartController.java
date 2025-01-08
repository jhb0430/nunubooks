package com.jhb0430.nunubooks.cart;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.service.CartService;

@RequestMapping("/nunubooks")
@Controller
public class CartController {

	private CartService cartService;
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// 장바구니 리스트 출력
	// 장바구니 db의 itemId와 BookDTO의 아이템아이디 가져와야함.
	@GetMapping("/cart")
	public String cartList(Model model) {
		
		List<Cart> cartList = cartService.cartList();
		
		model.addAttribute("cart",cartList);
		
		
		return "order/cart";
	}
	
}
