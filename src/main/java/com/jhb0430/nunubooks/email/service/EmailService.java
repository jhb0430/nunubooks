package com.jhb0430.nunubooks.email.service;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jhb0430.nunubooks.user.service.UserService;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	private final UserService userService;
	
	public void sendMimeMessage(String email) {
		//https://baby9235.tistory.com/131
		//https://velog.io/@tjddus0302/Spring-Boot-%EB%A9%94%EC%9D%BC-%EB%B0%9C%EC%86%A1-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-Gmail
		//https://blogan99.tistory.com/101 << 얘로간다 
		// 소리지르고싶다
		
		// 임시 비밀번호 생성 ㅇ
		// 임시 비밀번호 생성과 동시에 회원의 비밀번호가 임시 비밀번호로 수정됨 -> 여기서부터는 userService에서 해야할듯..?
		// 임시 비밀번호는 해싱코드로 변환되어야함 (임시지만 유출되면 안되니까 )
		// 마이페이지에서 회원정보 수정 페이지 만들기
		// 회원 정보 수정에서 비밀번호 등 변경 가능하도록 구현하기 
		
		//https://byul91oh.tistory.com/466
		
		int length= 10;
		
		char[] charSet = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'!', '@', '#', '$', '%', '^', '&' };
	    SecureRandom random = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder();
	    random.setSeed(new Date().getTime());
	  
	    int idx = 0;
	    int len = charSet.length;
	    for (int i=0; i< length; i++) {
	    	// idx = (int) (len * Math.random());
	    	idx = random.nextInt(len);    
	    	// 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
	    	stringBuilder.append(charSet[idx]);
	    }
	    
	    String rpw = stringBuilder.toString();
		
		
		
		
		
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일을 받을 수신자 설정
            mimeMessageHelper.setTo(email);
            // 메일의 제목 설정
            mimeMessageHelper.setSubject("누누북스 임시 비밀번호 안내 메일입니다.");

            // html 문법 적용한 메일의 내용
            String content1 = """
				<!DOCTYPE html>
                    <html xmlns:th="http://www.thymeleaf.org">
                                        
                    <body>
                    <div style="margin:100px;">
                        <h1> 발급된 임시 비밀번호는 </h1>
                        <br>
                                        
                                        
                        <div align="center" style="border:1px solid black;">
                            <h3>
                    """;
            String content2 = """
		       		       </h3>
                            <h3> 입니다 </h3>
                        </div>
					<div align="center"><a href="http://localhost:8080/nunubooks/account/login">로그인</a></div>
                        <br/>
                    </div>
                                                                          
                    </body>
                    </html>
				
                
                    """;
            
            /*
             <!DOCTYPE html>
				<html lang="ko" 
				    xmlns:th="http://www.thymeleaf.org">
				<meta charset="UTF-8">
				<head>
					<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
					<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
					<link rel="preconnect" href="https://fonts.googleapis.com">
					<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
					<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
				</head>
                <body>
                
                <div class="col-4 border">
					<div class="my-4">
						<span>발급된 임시 비밀번호는  </span>
						<span class="text-success font-weight-bold">00000</span>
						<span>입니다</span>
					</div>
					<a href="/nunubooks/account/login" class="btn btn-outline-secondary mb-4">로그인</a>
				</div>
                                        
                                        
				<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
				<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
                </body>
                </html>
             */
            
            // 메일의 내용 설정
            mimeMessageHelper.setText(content1 + rpw + content2, true);

            javaMailSender.send(mimeMessage);
            userService.updateTmpPassWord(email, rpw);

            log.info("메일 발송 성공!");
        } catch (Exception e) {
            log.info("메일 발송 실패!");
            throw new RuntimeException(e);
        }
	}
	
	
	
	
	

	
}
