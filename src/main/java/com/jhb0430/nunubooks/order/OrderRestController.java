package com.jhb0430.nunubooks.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.order.service.OrderService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/order")
@RestController
public class OrderRestController {

	private OrderService orderService;
	
	public OrderRestController(OrderService orderService) {
		
		this.orderService = orderService;
	}
	
	// 주문 db에 추가
	@PostMapping("/order-add")
	public Map<String,String> completeOrder(
			@RequestParam("name") String name
			,@RequestParam("phoneNumber") String phoneNumber
			,@RequestParam("postcode") String postcode
			,@RequestParam("address") String address
			,@RequestParam("totalPrice") int totalPrice
			,@RequestParam("shippingFee") int shippingFee
			,@RequestParam("payments") String payments
			,@RequestParam("point") int point
			,@RequestParam("savePoint") int savePoint
			,HttpSession session
			){
	
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String,String> resultMap = new HashMap<>();
		
		if(userId == 0) { //로그인 정보가 없으면
			resultMap.put("result", "fail");
		}
		
		if(orderService.addOrder(userId, name, phoneNumber, postcode, address, totalPrice, shippingFee, payments, point, savePoint)) {
			resultMap.put("result", "success");
		} else{
			resultMap.put("result", "fail");
			
		}
		return resultMap;
		
	}
	
	
	

    /*
if (userId == 0) {
        resultMap.put("result", "fail");
        resultMap.put("message", "로그인 정보가 없습니다.");
        return resultMap;
    }
 // 주문 생성 및 주문 번호 반환
    Integer orderId = orderService.createOrder(userId, name, phoneNumber, postcode, address, totalPrice, shippingFee, payments, point, savePoint);

    if (orderId != null) {
        resultMap.put("result", "success");
        resultMap.put("merchant_uid", orderId);  // 주문 번호 반환
    } else {
        resultMap.put("result", "fail");
        resultMap.put("message", "주문 생성 실패");
    }

    return resultMap;
}


     * 포트원(아임포트) 결제 완료 후 서버 검증 및 주문 저장

    @PostMapping("/payment/complete")
    public Map<String, String> verifyAndCompleteOrder(
            @RequestParam("imp_uid") String impUid,
            @RequestParam("merchant_uid") String merchantUid,
            @RequestParam("amount") int amount,
            HttpSession session
    ) {
        Map<String, String> resultMap = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null || userId == 0) {
            resultMap.put("result", "fail");
            resultMap.put("message", "로그인 정보가 없습니다.");
            return resultMap;
        }

        try {
            // 포트원(아임포트) Access Token 발급
            String accessToken = orderService.getPortoneAccessToken();

            // 결제 정보 검증
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(
                    "https://api.iamport.kr/payments/" + impUid,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            Map<String, Object> responseBody = (Map<String, Object>) response.getBody().get("response");
            int paidAmount = (int) responseBody.get("amount");

            // 결제 금액 검증
            if (amount != paidAmount) {
                resultMap.put("result", "fail");
                resultMap.put("message", "결제 금액이 일치하지 않습니다.");
                return resultMap;
            }

            // 결제 검증 성공 후 주문 저장
            if (orderService.addOrderAfterPayment(userId, merchantUid, paidAmount)) {
                resultMap.put("result", "success");
            } else {
                resultMap.put("result", "fail");
                resultMap.put("message", "주문 저장 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", "fail");
            resultMap.put("message", "서버 오류 발생");
        }

        return resultMap;
    }
     */
	
	@DeleteMapping("/delete")
	public Map<String,String> deleteOrder(
			@RequestParam("id") int id
			,HttpSession session){
				
		int userId = (Integer)session.getAttribute("userId");
		
Map<String,String> resultMap = new HashMap<>();
		
		if(userId == 0) {
			resultMap.put("result", "fail");
		}
		
		
		if(orderService.deleteOrder(id, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
			
		}
		return resultMap;
	}
	
	
	
}
