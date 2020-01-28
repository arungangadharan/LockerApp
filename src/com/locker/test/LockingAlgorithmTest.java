package com.locker.test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.locker.LockingAlgorith;

public class LockingAlgorithmTest {

	@Test
	public void test() throws Exception {
		
		try {
		//messageMain = "to".getBytes("UTF-8");
//		String encMainString =  DatatypeConverter.printBase64Binary(messageMain);
//		System.out.println(encMainString);
//		
//		byte[] messageMain1 = "geek".getBytes("UTF-8");
//		String encMainString1 =  DatatypeConverter.printBase64Binary(messageMain1);
//		System.out.println(encMainString1);
//		
//		byte[] messageMain2 = "geeke".getBytes("UTF-8");
//		String encMainString2 =  DatatypeConverter.printBase64Binary(messageMain2);
//		System.out.println(encMainString2);
		
		LockingAlgorith lAlgo = new LockingAlgorith();
		String encSecret = lAlgo.encryptString("I am tosh", "crap");
		System.out.println(encSecret);
		
		String decSecret = lAlgo.decryptString(encSecret, "dest");
		System.out.println(decSecret);
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
