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
	
	
	
	
}
