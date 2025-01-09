package com.jhb0430.nunubooks.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Data {

	private String title;
	private String link;
	private String author;
	private String description;
	private String pubDate;
	private String isbn;
	private String isbn13;
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
	
	private Subinfo subInfo;
}
