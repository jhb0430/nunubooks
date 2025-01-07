package com.jhb0430.nunubooks.books.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	//private int id;
	
	private String title;
	
	private String link;
	
	private String author;
	
	private String pubDate;
	
	private String isbn;
	
	private int isbn13;
	
	private int itemId;
	
	private int priceSales;
	
	private int priceStandard;
	
	private String mallType;
	
	private String stockStatus;
	
	private int mileage;
	
	private String cover;
	
	private int categoryId;
	
	private String categoryName;
	
	private String publisher;
	
	private int salesPoint;
	
	private boolean adult;
	
	private boolean fixedPrice;
	
	
	
/*
	 {

  "item": [
    {
      "title": "좌파내란을 탄핵한다 - 내란이 아니라 내전이다",
      "link": "http://www.aladin.co.kr/shop/wproduct.aspx?ItemId=354945952&amp;partner=openAPI&amp;start=api",
      "author": "전영준 (지은이)",
      "pubDate": "2025-01-31",
      "description": "한국 정치의 현실을 직시하며, 체제전복과 헌정 질서 파괴라는 민감한 주제를 담고 있다. 저자 전영준은 자신의 정치적 소신과 분석력을 통해 복잡한 정치적 사건을 명료하게 해석한다.",
      "isbn": "K732035557",
      "isbn13": "9791194005247",
      "itemId": 354945952,
      "priceSales": 16200,
      "priceStandard": 18000,
      "mallType": "BOOK",
      "stockStatus": "예약판매",
      "mileage": 900,
      "cover": "https://image.aladin.co.kr/product/35494/59/coversum/k732035557_1.jpg",
      "categoryId": 51233,
      "categoryName": "국내도서>사회과학>정치학/외교학/행정학>정치학 일반",
      "publisher": "투나미스",
      "salesPoint": 0,
      "adult": false,
      "fixedPrice": true,
      "customerReviewRank": 0,
      "subInfo": { }
    },8
    
      "version": "20131101",
  "logo": "http://image.aladin.co.kr/img/header/2011/aladin_logo_new.gif",
  "title": "알라딘 전체 신간 리스트 - 국내도서",
  "link": "https://www.aladin.co.kr/shop/common/wnew.aspx?NewType=New&amp;BranchType=1&amp;partner=openAPI",
  "pubDate": "Thu, 02 Jan 2025 09:29:13 GMT",
  "totalResults": 1000,
  "startIndex": 1,
  "itemsPerPage": 10,
  "query": "QueryType=ITEMNEWALL;SearchTarget=Book",
  "searchCategoryId": 0,
  "searchCategoryName": "국내도서",
    
 **/
}
