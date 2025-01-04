package com.jhb0430.nunubooks.books.dto;

import java.util.List;

import com.jhb0430.nunubooks.books.domain.Data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BookDTO {
	

	private String version;
	private String logo;
	private String title;
	private String link;
	private String pubDate;
	private int totalResults;
	private int startIndex;
	private int itemsPerPage;
	private String query;
	private int searchCategoryId;
	private String searchCategoryName;
	
	private List<Data> item;
	
	
	/*
	public class Response{
	
		
		
	}
	
  public class Item {
	  List<Data> item;
  }
	
//	private int total;
//    List<Items> items = new ArrayList<>();
    
	public class Data {
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
	}
	
	*/
	
	
}
