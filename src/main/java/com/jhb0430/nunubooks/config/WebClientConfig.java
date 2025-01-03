package com.jhb0430.nunubooks.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	
	DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
	
	HttpClient httpClient = HttpClient.create()
			  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
			  .responseTimeout(Duration.ofMillis(5000))
			  .doOnConnected(conn -> 
			    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
			      .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

			WebClient client = WebClient.builder()
			  .clientConnector(new ReactorClientHttpConnector(httpClient))
			  .build();
	
			/*    	
	@Bean
		public WebClient webClient() {
	      	factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
	       
	      	return WebClient.builder()
	                .uriBuilderFactory(factory)
	                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
	                .clientConnector(new ReactorClientHttpConnector(httpClient))
	                .build();
		}
	      	
  	@Bean
  	public ConnectionProvider connectionProvider() {
  	    return ConnectionProvider.builder("http-pool")
  	            .maxConnections(100)                    // connection pool의 갯수
  	            .pendingAcquireTimeout(Duration.ofMillis(0)) //커넥션 풀에서 커넥션을 얻기 위해 기다리는 최대 시간
  	            .pendingAcquireMaxCount(-1)             //커넥션 풀에서 커넥션을 가져오는 시도 횟수 (-1: no limit)
  	            .maxIdleTime(Duration.ofMillis(1000L))        //커넥션 풀에서 idle 상태의 커넥션을 유지하는 시간
  	            .build();
  		}
			
			 */

	
	@Bean
	public WebClient webClient(WebClient.Builder webClient) {
		
		return webClient
				.baseUrl("http://localhost:8080")
				.defaultHeaders(httpHeaders -> {
					httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
					httpHeaders.add("apiKey", "API Key 값 입력");
				})
				.build();
	}
	
			
}
