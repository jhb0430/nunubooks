package com.jhb0430.nunubooks.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jhb0430.nunubooks.email.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nunubooks")
@RequiredArgsConstructor
public class EmailRestController {

		    private final EmailService mailService;

		    @GetMapping("/send-email")
		    public void sendMimeMessage(@RequestParam("email") String email) {
		        mailService.sendMimeMessage(email);
		    }
	
}
