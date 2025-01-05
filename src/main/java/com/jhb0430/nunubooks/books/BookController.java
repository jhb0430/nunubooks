package com.jhb0430.nunubooks.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;

import reactor.core.publisher.Mono;

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
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/searchList")
	public String bookList(
					Model model
							) {
		 WebClient webClient = webClientBuilder.build();
		 
		 Mono<BookDTO> response = 
				 webClient.get()
					.uri(
					"https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?"
					+ "ttbkey=ttbleky22241703001"
					+ "&"
					+ "Query=aladdin"
					+ "&"
					+ "QueryType=Title"
					+ "&"
					+ "MaxResults=10"
					+ "&"
					+ "start=1"
					+ "&"
					+ "SearchTarget=Book"
					+ "&"
					+ "output=js"
					+ "&"
					+ "Version=20131101"
					) 
			.retrieve()
			.bodyToMono(BookDTO.class);
		 
		 BookDTO bookDTO = response.block();
		 model.addAttribute("books",bookDTO.getItem());
		 
	    return "books/searchList";
	}
	
}
