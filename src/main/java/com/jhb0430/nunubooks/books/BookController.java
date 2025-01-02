package com.jhb0430.nunubooks.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nunubooks")
@Controller
public class BookController {

	
	@GetMapping("/search")
	public String searchList() {
		
		return "books/search";
	}
	@GetMapping("/product")
	public String bookInfo() {
		
		return "books/product";
	}
}
