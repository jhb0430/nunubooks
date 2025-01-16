package com.jhb0430.nunubooks.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/nunubooks/order")
@Controller
public class OrderController {

	@GetMapping("order")
	public String bookOrder() {
		
/*
 * ++ 사용가능 포인트 박스 추가
++ 결제 수단 박스 추가

장바구니에 있는 아이템 정보
로그인된 유저 정보

로그인된 유저의 장바구니에 있는 목록을 구매한다 

로그인된 유저의 주소, 이름, 전화번호를 불러온다.


*/
		return "order/order-page";
	}
	
	
	
	
}
