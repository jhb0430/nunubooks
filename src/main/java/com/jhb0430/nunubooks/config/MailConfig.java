package com.jhb0430.nunubooks.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	
	@Bean
	public JavaMailSender javaMailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		//SMTP Server 설정
		// Google SMTP Server 주소
		mailSender.setHost(host);
		// Google SMTP Por 넘버
		mailSender.setPort(port);
		
		// 인증 정보 설정
		// 실제 사용할 gmail 주소
		mailSender.setUsername(username);
		//gmail 비밀번호
		mailSender.setPassword(password);
		
		// 추가설정 
		Properties props = mailSender.getJavaMailProperties();
		
		// SMTP 전송 프로토콜 사용 명시
		props.put("mail.transport.protocol", "smtp");
		//SMTP 인증 사용 명시
		props.put("mail.smtp.auth", "true");
		// TLS 시작 연결 사용 설정 (보안)
		props.put("mail.smtp.starttls.enable", "true");
		// 디버그 로그 활성화
		props.put("mail.debug", "true");
		
		return mailSender;
		//https://baby9235.tistory.com/131
	}
}
