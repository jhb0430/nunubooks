package com.jhb0430.nunubooks.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.jhb0430.nunubooks.books.dto.BookDTO;

import reactor.core.publisher.Mono;


@RestController
public class BookRestController {

	/*
	@Autowired
	WebClient client = WebClient.create();
*/	

	@Autowired
    private WebClient.Builder webClientBuilder;

	
	
	/*
	@GetMapping("/nunubooks/test")
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
		
	}
	 */	
	
	@GetMapping("/nunubooks/test")
	public BookDTO bookTest(
//			@RequestParam("query") String query
			)  {
		
		 WebClient webClient = webClientBuilder.build();
		
		 
		 
		 Mono<BookDTO> response = 
//			webClient.get().uri("https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?ttbkey=ttbleky22241703001&QueryType=Title&MaxResults=10&start=1&SearchTarget=Book&output=js&Version=20131101&"
//			+ Query //&Query=aladdin
			webClient.get()
			// uriBuilder  사용법 검색해보기
/*
			 메서드	설명	예시
scheme()	URL의 프로토콜을 설정합니다.		https, http
host()	도메인 이름을 설정합니다.				www.aladin.co.kr
port()	포트 번호를 설정합니다.				8080
path()	경로를 설정합니다.					/ttb/api/ItemSearch.aspx
queryParam()	쿼리 파라미터를 추가합니다.		Query=aladdin
build()	설정한 값들을 조합해 최종 URI를 생성합니다.	https://www.aladin.co.kr/...
			 * */
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
			.retrieve()
			.bodyToMono(BookDTO.class);
	    return response.block();
	}



	
}
