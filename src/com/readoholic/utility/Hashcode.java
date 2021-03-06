package com.readoholic.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public final class Hashcode {
	private Hashcode(){
		
	}
	public static String generate(String[] strings){
		String hashcode="";
		String hashtext;
		for(String s: strings){
			hashcode += s;
		}
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(hashcode.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
