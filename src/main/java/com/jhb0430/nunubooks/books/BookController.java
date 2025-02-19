package com.jhb0430.nunubooks.books;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.domain.Packing;
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
	
	
	@GetMapping("/main")
	public String mainPage(@RequestParam(value = "queryType", required = false) String queryType
			,Model model) {
		
//		QueryType


		String bestSeller = "Bestseller";
		String newItem = "ItemNewAll"; //		ItemNewAll : 신간 전체 리스트
		String newSpecial = "ItemNewSpecial"; //		ItemNewSpecial : 주목할 만한 신간 리스트
		String editerChoice = "ItemEditorChoice"; //		ItemEditorChoice : 편집자 추천 리스트
		
		queryType = newItem;
		BookDTO newBook = bookService.mainBookList(queryType);
		model.addAttribute("newBook",newBook);
		
		queryType = bestSeller;
		BookDTO best = bookService.mainBookList(queryType);
		model.addAttribute("best",best);
		
		queryType = newSpecial;
		BookDTO specialNew = bookService.mainBookList(queryType);
		model.addAttribute("special",specialNew);
		
		
		
		return "books/main";
	}
	
	
	// 신간,베스트셀러, 화제의 도서 전체를 다룸 
	@GetMapping("/books")
	public String bestSellerList(
					@RequestParam(value="queryType" , defaultValue = "Bestseller") String queryType
					,@RequestParam(value="maxResults" , defaultValue = "10") int maxResults
					,@RequestParam(value="outofStockfilter" , defaultValue = "0") int outofStockfilter
					,@RequestParam(value = "year", required = false) Integer year
				    ,@RequestParam(value = "month", required = false) Integer month
				    ,@RequestParam(value = "week", required = false) Integer week
					,Model model
					) {
		    
		
		 LocalDate now = LocalDate.now();
//			“Year=2022&Month=5&Week=3”형식으로 요청.
//			생략하면 현재 주간의 정보 제공.
//		    int nowWeek = (Integer)now.get(WeekFields.ISO.weekOfMonth()) -1; // 알라딘은 주차가 -1 인가
		    int nowYear = (Integer)now.getYear();
		    int nowMonth = (Integer)now.getMonthValue();
		    
//		    int nowWeek = now.get(WeekFields.ISO.weekOfMonth()) == 0 ? 1 : now.get(WeekFields.ISO.weekOfMonth()) -1;
		    int nowWeek = now.get(WeekFields.ISO.weekOfMonth());
		    if (nowWeek == 0) {
		        nowWeek = 1;  // 0주이면 1주로 바꿈.
		    }  else if (nowWeek > 1) {
		        nowWeek = nowWeek - 1;  // 1주 이상인 경우 -1을 처리
		    }
 
		    
		    if (year == null || month == null || week == null) {
		    	year = nowYear;
		    	month = nowMonth;
		    	week = nowWeek;
		    }
		    
		    
		    
		    
		    model.addAttribute("nowYear",year);
		    model.addAttribute("nowMonth",month);
		    model.addAttribute("nowWeek",week);
		
		
		 BookDTO bookDTO = bookService.bestSeller(queryType, maxResults, outofStockfilter, year, month, week);
		 model.addAttribute("seller",bookDTO);
		 
		 model.addAttribute("maxResults",maxResults);
		 model.addAttribute("queryType",queryType);
		 model.addAttribute("outofStock",outofStockfilter);
		
		
		return "books/best-seller";
	}
	
	
	@GetMapping("/product")
	public String bookInfo(
			@RequestParam("itemId") int itemId
			,Model model) {
		
		WebClient webClient = webClientBuilder.build();
		 
		
		 BookDTO bookDTO = bookService.bookProduct(itemId);
		 
		 model.addAttribute("book",bookDTO);
		
		return "books/product";
	}
	

	
	@GetMapping("/searchList")
	public String bookList(
			@RequestParam("query") String query
			,@RequestParam(value="maxResults" , defaultValue = "10") int maxResults
//			, @RequestParam(value = "page", defaultValue = "1") int page
			,@RequestParam(value = "start", defaultValue = "1") int start
			,@RequestParam(value="outofStockfilter" , defaultValue = "0") int outofStockfilter
			,Model model) {
		
		 WebClient webClient = webClientBuilder.build();
		 
		 // maxResult가 필수값이 아니게 설정해주기 
		 // 검색시 결과창에 내각 검색한 값 띄울 수 있게 하기
//		 if((Integer)maxResults == null) {
			 
//		 }
//		 int page = (start - 1) / maxResults + 1; 
		 int page = start; // start가 페이지 번호이므로 그대로 사용
//		 int offset = (page - 1) * maxResults; // 검색 결과에서 시작하는 인덱스 계산

		 
		 BookDTO bookDTO = bookService.fetchBooks(query, maxResults, page + 1,outofStockfilter);
		 model.addAttribute("books",bookDTO);
		 model.addAttribute("query",query);
		 model.addAttribute("maxResults",maxResults);
		 model.addAttribute("page", page); 
		 model.addAttribute("outofStock", outofStockfilter); 
		 
		 
	    return "books/searchList";
	}
	
}
