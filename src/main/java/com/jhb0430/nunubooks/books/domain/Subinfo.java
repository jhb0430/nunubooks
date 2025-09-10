package com.jhb0430.nunubooks.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Subinfo {

	private String subTitle;
	private String originalTitle;
	private String subbarcode;
	private int itemPage;
	private boolean taxFree;
	private String bestSellerRank;
	private Packing packing;
	
}
