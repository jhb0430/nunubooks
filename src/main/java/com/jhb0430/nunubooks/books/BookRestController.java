package com.jhb0430.nunubooks.books;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


@RestController
public class BookRestController {

	/*
	@Autowired
	WebClient client = WebClient.create();
*/	

	@Autowired
    private WebClient.Builder webClientBuilder;

	
	
	

	@GetMapping("/nunubooks/test")
	public Map<String,Object> bookTest(
			//@RequestParam("Query") String Query
			)  {
		
		 WebClient webClient = webClientBuilder.build();
		
	    Mono<Map> response = 
			webClient.get().uri("https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbleky22241703001&Query=aladdin&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101"
			//+ Query
					) 
			.retrieve()
			.bodyToMono(Map.class);
	    
	    return response.block();
		
	}
		
}
