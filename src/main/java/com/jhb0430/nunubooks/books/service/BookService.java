package com.jhb0430.nunubooks.books.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhb0430.nunubooks.aladdin.AladdinOpenAPI;
import com.jhb0430.nunubooks.books.domain.Books;

@Service
public class BookService {

	private AladdinOpenAPI aladdinOpenAPI;
	
	public BookService(AladdinOpenAPI aladdinOpenAPI) {
		this.aladdinOpenAPI = aladdinOpenAPI;
	}
	
	
	
	public List<Books> searchBooks(String searchWord) {
		

		// GetUrl = > http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx? + searchWord 
	}
	
	
}
