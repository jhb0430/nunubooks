package com.jhb0430.nunubooks.books.service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;

import reactor.core.publisher.Mono;

@Service	
public class BookService {

	@Autowired
	WebClient.Builder webClientBuilder;
	
	
	
	public BookDTO fetchBooks(String query
			,int maxResults
			,int start
			,int outofStockfilter
			) {
		
		 WebClient webClient = webClientBuilder.build();
		 
		 
		 Mono<BookDTO> response = 
				 webClient.get()
					.uri(uriBuilder -> uriBuilder
							.scheme("https")
							.host("www.aladin.co.kr")
//							.port(8080)
							.path("/ttb/api/ItemSearch.aspx")
							.queryParam("ttbkey","ttbleky22241703001")
							.queryParam("Query",query)
							.queryParam("QueryType","Title")
							.queryParam("MaxResults",maxResults)
							.queryParam("start",start)
							.queryParam("SearchTarget","Book")
							.queryParam("outofStockfilter",outofStockfilter)
							.queryParam("output","js")
							.queryParam("Version","20131101")
							.build()
							)
					.retrieve()
					.bodyToMono(BookDTO.class);
			    return response.block();
	}
	
	
	public BookDTO bookProduct(int itemId) {
		
		WebClient webClient = webClientBuilder.build();
		
		Mono<BookDTO> response = 
				webClient.get()
				.uri(uriBuilder -> uriBuilder
						.scheme("https")
						.host("www.aladin.co.kr")
						.path("/ttb/api/ItemLookUp.aspx")
						.queryParam("ttbkey","ttbleky22241703001")
						.queryParam("itemIdType","itemId")
						.queryParam("itemId",itemId)
						.queryParam("Cover","Big")
						.queryParam("output","js")
						.queryParam("Version","20131101")
//OptResult=ebookList,usedList,reviewList
						.build()
						)
				.retrieve()
				.bodyToMono(BookDTO.class);
		return response.block();
	}
	


	public BookDTO bestSeller(
			String queryType
			,int maxResults
			, int outofStockfilter 
			,int year
			,int month
			,int week
			) {
		
		WebClient webClient = webClientBuilder.build();
		
		// now값을 컨트롤러에서 가져온다...? 


	    
		Mono<BookDTO> response = 
				webClient.get()
				.uri(uriBuilder -> uriBuilder
						.scheme("https")
						.host("www.aladin.co.kr")
						.path("/ttb/api/ItemList.aspx")
						.queryParam("ttbkey","ttbleky22241703001")
						.queryParam("QueryType",queryType)
//						.queryParam("QueryType","Bestseller")
						.queryParam("MaxResults",maxResults)
						.queryParam("Year",year)
						.queryParam("Month",month)
						.queryParam("Week",week)
						.queryParam("SearchTarget","Book")
						.queryParam("start",1)
						.queryParam("outofStockfilter",outofStockfilter)
						.queryParam("output","js")
						.queryParam("Version","20131101")
						.build()
						)
				.retrieve()
				.bodyToMono(BookDTO.class);
			return response.block();
	}
	


	public BookDTO mainBookList(String queryType) {
		
		 WebClient webClient = webClientBuilder.build();
		 
		 
		 Mono<BookDTO> response = 
					webClient.get()
					.uri(uriBuilder -> uriBuilder
							.scheme("https")
							.host("www.aladin.co.kr")
							.path("/ttb/api/ItemList.aspx")
							.queryParam("ttbkey","ttbleky22241703001")
							.queryParam("QueryType",queryType)
							.queryParam("SearchTarget","Book")
							.queryParam("Cover","MidBig")
							.queryParam("start",1)
							.queryParam("output","js")
							.queryParam("Version","20131101")
							.build()
							)
					.retrieve()
					.bodyToMono(BookDTO.class);
				return response.block();
	}
	
	

	}
	
	
