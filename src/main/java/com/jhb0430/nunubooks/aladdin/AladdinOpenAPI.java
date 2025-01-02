package com.jhb0430.nunubooks.aladdin;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class AladdinOpenAPI {

	private static final String BASE_URL = "http://www.aladdin.co.kr/ttb/api/ItemSearch.aspx?";

	public static String GetUrl(String searchWord) throws Exception {
		Map<String,String> hm = new HashMap<String,String>();
		hm.put("ttbkey", "ttbleky22241703001");
		hm.put("Query", URLEncoder.encode(searchWord, "UTF-8"));
		hm.put("QueryType", "Title");
		hm.put("MaxResults", "10");
		hm.put("start", "1");
		hm.put("SearchTarget", "Book");
		hm.put("output", "xml");

		StringBuffer sb = new StringBuffer();
		
		Iterator<String> iter = hm.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String val  = hm.get(key);
			sb.append(key).append("=").append(val).append("&");
		}

		return BASE_URL + sb.toString();
	}

	public static void main(String[] args) throws Exception {
		String url = GetUrl(args[0]);
		AladdinOpenAPIHandler api = new AladdinOpenAPIHandler();
		api.parseXml(url);
		for(Item item : api.Items){
			System.out.println(item.Title + " : " + item.Link);
		}
	}
	
}
