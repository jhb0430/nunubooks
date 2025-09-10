package com.jhb0430.nunubooks.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.email.service.EmailService;
import com.jhb0430.nunubooks.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailRestController {

		    private final EmailService mailService;
		    private final UserService userService;

		    @PostMapping("/send-email")
		    public Map<String, Boolean> sendMimeMessage(
			    		@RequestParam("name") String name
			    		,@RequestParam("loginId") String loginId
			    		,@RequestParam("email") String email
			    		) {
		    	
		    	Map<String, Boolean> resultMap = new HashMap<>();
		    	if(userService.findUserforSetPw(name, loginId, email)) {
		    		mailService.sendMimeMessage(email);
		    		resultMap.put("result",true );
		    	} else {
		    		resultMap.put("result",false );
		    	}
		    	return resultMap;
		    }
	
}
