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
							.queryParam("start",1)
							.queryParam("SearchTarget","Book")
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
	


	public BookDTO bestSeller(int maxResults, int outofStock ,String period) {
		
		WebClient webClient = webClientBuilder.build();
		
		 LocalDate now = LocalDate.now();
//			“Year=2022&Month=5&Week=3”형식으로 요청.
//			생략하면 현재 주간의 정보 제공.
		    int week = now.get(WeekFields.ISO.weekOfMonth()) -1; // 알라딘은 주차가 -1 인가
		    int year = now.getYear();
		    int month = now.getMonthValue();
		
		    //default = week=1.,2,3,4
		    // 월간 선택시 = year=2025&month=1
		    // 연간 선택시 year=2025 month=0, week=0;
//		    if(period == "year") {
//		    	
//		    }
		    
//		    switch(period) {
		    switch(period.toLowerCase()) {
		    
			    case "week":
			    break;
			    
			    case "month" : 	
			    	year= 0;
			    	week = 0;
			    break;
			    
			    case "year" : 
			    	month = 0;
			    	week= 0;
			    break;
			    
			    default: // 예외처리..하래
		            throw new IllegalArgumentException(period + "는 사용하실 수 없습니다");
		    }
		    
		    
		    //Local variable year defined in an enclosing scope must be final or effectively final
		    final int finalYear = year;
		    final int finalMonth = month;
		    final int finalWeek = week;
		    
		Mono<BookDTO> response = 
				webClient.get()
				.uri(uriBuilder -> uriBuilder
						.scheme("https")
						.host("www.aladin.co.kr")
						.path("/ttb/api/ItemList.aspx")
						.queryParam("ttbkey","ttbleky22241703001")
						.queryParam("QueryType","Bestseller")
						.queryParam("MaxResults",maxResults)
						.queryParam("Year",finalYear)
						.queryParam("Month",finalMonth)
						.queryParam("Week",finalWeek)
						.queryParam("SearchTarget","Book")
						.queryParam("start",1)
						.queryParam("outofStockfilter",outofStock)
						.queryParam("output","js")
						.queryParam("Version","20131101")
						.build()
						)
				.retrieve()
				.bodyToMono(BookDTO.class);
			return response.block();

			
//			QueryType
//			ItemNewAll : 신간 전체 리스트
//			ItemNewSpecial : 주목할 만한 신간 리스트
//			ItemEditorChoice : 편집자 추천 리스트
//			(카테고리로만 조회 가능 - 국내도서/음반/외서만 지원)
//			Bestseller : 베스트셀러
//			BlogBest : 블로거 베스트셀러 (국내도서만 조회 가능)
//			Year, Month, Week
//			베스트셀러를 조회할 주간 (기본값:0)
//			QueryType=Bestseller인 경우,
//			베스트셀러를 조회할 주간
//			“Year=2022&Month=5&Week=3”형식으로 요청.
//			생략하면 현재 주간의 정보 제공.
//			outofStockfilter
//			양의정수(기본값:0)
//			품절/절판 등 재고 없는 상품 필터링
//			("1"이 제외 필터)





			
			
	}
	
	
	}
	
	
