package com.locker;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

public class LockingAlgorith  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String encryptString(String strToEncrypt, String seed) throws UnsupportedEncodingException{
		
		byte[] messageMain = strToEncrypt.getBytes("UTF-8");
		String encMainString =  DatatypeConverter.printBase64Binary(messageMain);		
		byte[] messageSeed = seed.getBytes("UTF-8");
		String encSeedString =  DatatypeConverter.printBase64Binary(messageSeed);
		
		return stringMash(encMainString, encSeedString );
		
		
	}
	
	public String decryptString(String strToDecrypt, String seed) throws UnsupportedEncodingException, Exception{
		
		
		
		byte[] messageSeed = seed.getBytes("UTF-8");
		String encSeedString =  DatatypeConverter.printBase64Binary(messageSeed);
//		System.out.println("Seed String - enc ==>"+encSeedString);
		String recovered = stringUnmash(strToDecrypt, encSeedString );
//		System.out.println("Recovered String - enc ==>"+recovered);
		return new String(DatatypeConverter.parseBase64Binary(recovered)); 
		
		
	}
	
	private String stringMash(String str1, String str2){
		
		StringBuffer sBuf = new StringBuffer();
		int len = (str1.length() <= str2.length()) ? str1.length() : str2.length();
		String pendingStr = (str1.length() <= str2.length()) ? str2.substring(len, str2.length()) : str1.substring(len, str1.length());
		
		for( int i = 0; i < len; i++){
			sBuf.append(str1.charAt(i)).append(str2.charAt(i));
		}
		sBuf.append(pendingStr);
		
//		System.out.println("Mashed String - enc ==>"+sBuf.toString());
		return sBuf.toString();
	}
	
	private String stringUnmash(String str1, String str2) throws Exception{
		
		StringBuffer sBuf = new StringBuffer();
		int len = (str2.length() * 2);
		
//		System.out.println("Mashed String - enc ==>"+str1);
		for( int i =0; i < str2.length(); i=i+1){
			if(str2.charAt(i) != str1.charAt((i*2)+1)){
				throw new Exception ("Wrong seed, failed");
			}
		}
			
		for( int i =1; i < len; i=i+2){
		
			sBuf.append(str1.charAt(i-1));
		}
		sBuf.append(str1.substring(len, str1.length()));
		return sBuf.toString();
	}
	
	public static void main(String[] args) {
		try {
//			messageMain = "to".getBytes("UTF-8");
//			String encMainString =  DatatypeConverter.printBase64Binary(messageMain);
//			System.out.println(encMainString);
//			
//			byte[] messageMain1 = "geek".getBytes("UTF-8");
//			String encMainString1 =  DatatypeConverter.printBase64Binary(messageMain1);
//			System.out.println(encMainString1);
//			
//			byte[] messageMain2 = "geeke".getBytes("UTF-8");
//			String encMainString2 =  DatatypeConverter.printBase64Binary(messageMain2);
//			System.out.println(encMainString2);
			
			LockingAlgorith lAlgo = new LockingAlgorith();
			String encSecret = lAlgo.encryptString("I am tosh", "crap");
//			System.out.println(encSecret);
			
//			String decSecret = lAlgo.decryptString(encSecret, "crap");
//			System.out.println(decSecret);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
