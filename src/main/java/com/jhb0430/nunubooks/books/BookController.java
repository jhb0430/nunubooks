package com.jhb0430.nunubooks.books;

import java.time.LocalDate;
import java.time.temporal.WeekFields;

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
	
	
	@GetMapping("/main")
	public String mainPage() {
		
		return "books/main";
	}
	
	
	@GetMapping("/bestSeller")
	public String bestSellerList(
					@RequestParam(value="maxResults" , defaultValue = "10") int maxResults
					,@RequestParam(value="outofStock" , defaultValue = "0") int outofStock
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
		    
		    int nowWeek = now.get(WeekFields.ISO.weekOfMonth()) == 0 ? 1 : now.get(WeekFields.ISO.weekOfMonth()) -1;
		    
		    if (year == null || month == null || week == null) {
		    	year = nowYear;
		    	month = nowMonth;
		    	week = nowWeek;
		    }
		    
		    
		    
		    model.addAttribute("nowYear",year);
		    model.addAttribute("nowMonth",month);
		    model.addAttribute("nowWeek",week);
		
		
		 BookDTO bookDTO = bookService.bestSeller(maxResults, outofStock, year, month, week);
		 model.addAttribute("seller",bookDTO);
		 
		 model.addAttribute("maxResults",maxResults);
		
		
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
			,Model model) {
		 WebClient webClient = webClientBuilder.build();
		 
		 // maxResult가 필수값이 아니게 설정해주기 
		 // 검색시 결과창에 내각 검색한 값 띄울 수 있게 하기
//		 if((Integer)maxResults == null) {
			 
//		 }
		 
		 BookDTO bookDTO = bookService.fetchBooks(query,maxResults);
		 model.addAttribute("books",bookDTO);
		 model.addAttribute("query",query);
		 model.addAttribute("maxResults",maxResults);
		 
		 
		 
	    return "books/searchList";
	}
	
}
