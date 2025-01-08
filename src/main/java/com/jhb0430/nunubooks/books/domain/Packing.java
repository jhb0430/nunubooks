package com.jhb0430.nunubooks.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Packing {

private String styleDesc;
private int weight;
private int sizeDepth;
private int sizeHeight;
private int sizeWidth;

}
