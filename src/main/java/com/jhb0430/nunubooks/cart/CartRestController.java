package com.jhb0430.nunubooks.cart;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/cart")
@RestController
public class CartRestController {

	
	@PostMapping("/add")
	public Map<String, String> addCart(){
		
		
	}
}
