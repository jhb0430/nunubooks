package com.jhb0430.nunubooks.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;
import com.jhb0430.nunubooks.books.service.BookService;

import reactor.core.publisher.Mono;
@RequestMapping("/books")
@RestController
public class BookRestController {



	

	
	@GetMapping("/search")
	public BookDTO bookTest()  {
		
		
		return 

	}


	
	/*
	@Autowired
	WebClient client = WebClient.create();

	@Autowired
    private WebClient.Builder webClientBuilder;
	@Autowired
	private BookService bookService;

	
	public Map<String,Object> bookTest(
//			@RequestParam("Query") String Query
			)  {
		
		 WebClient webClient = webClientBuilder.build();
		
	    Mono<Map> response = 
//			webClient.get().uri("https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbleky22241703001&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101&"
			webClient.get().uri("https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbleky22241703001&Query=aladdin&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101"
			//+ Query
					) 
			.retrieve()
			.bodyToMono(Map.class);
	    
	    return response.block();
	    
//				uriBuilder  사용법 검색해보기
					.uri(
					"https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?"
					+ "ttbkey=ttbleky22241703001"
					+ "&"
					+ "Query=aladdin"
//					+ "Query=" + query
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
		
	}
	 */	
	
}
