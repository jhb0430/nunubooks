package com.jhb0430.nunubooks.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;
import com.jhb0430.nunubooks.books.service.BookService;

@RequestMapping("/nunubooks")
@Controller
public class BookController {

	private BookService bookService;
	private WebClient.Builder webClientBuilder;
	
	public BookController(BookService bookService, WebClient.Builder webClientBuilder) {
		this.bookService = bookService;
		this.webClientBuilder = webClientBuilder;
	}
	
	
	@GetMapping("/bestSeller")
	public String bestSellerList() {
		
		return "books/best-seller";
	}
	
	
	@GetMapping("/product")
	public String bookInfo(
			@RequestParam("itemId") String itemId
			,Model model) {
		
		WebClient webClient = webClientBuilder.build();
		 
		
		 BookDTO bookDTO = bookService.bookProduct(itemId);
		 model.addAttribute("book",bookDTO);
		
		return "books/product";
	}
	

	
	@GetMapping("/searchList")
	public String bookList(
			@RequestParam("query") String query
			,Model model) {
		 WebClient webClient = webClientBuilder.build();
		 
		
		 BookDTO bookDTO = bookService.fetchBooks(query);
		 model.addAttribute("books",bookDTO);
		 
		 
		 
	    return "books/searchList";
	}
	
}
