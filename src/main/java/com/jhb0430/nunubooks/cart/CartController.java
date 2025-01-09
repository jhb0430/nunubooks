package com.jhb0430.nunubooks.cart;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jhb0430.nunubooks.cart.domain.Cart;
import com.jhb0430.nunubooks.cart.service.CartService;

import jakarta.servlet.http.HttpSession;

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
	public String cartList(
//			@RequestParam("userId") int userId
			Model model
			,HttpSession session
			) {
		
//		model.addAttribute("cart",cartList);
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Cart> cartList = cartService.getCartList(userId);
		model.addAttribute("cartList",cartList);
		
		int cartCount = cartService.countCart(userId); 
		model.addAttribute("cartCount",cartCount);
		
		// 장바구니에 담긴 갯수도 가져와야함
//		cart?userId=000 로 받아와야할거같은디
		return "order/cart";
	}
	
}
