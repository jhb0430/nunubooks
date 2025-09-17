package com.jhb0430.nunubooks.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashingEncoder {

	
public static String encode(String message) {
		
		String result ="";
		
		try {
//			MessageDigest messageDigest = MessageDigest.getInstance("sha256");
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
		
			byte[] digest = messageDigest.digest();
			
			for (int i = 0; i < digest.length; i++) {
				result+= Integer.toHexString(digest[i] &0xff);
			}
		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return null;
			throw new RuntimeException(e); // 이러면 NULL 오류 날 일도 없다고 한다
		}
		return result;
		
	}
	
}
