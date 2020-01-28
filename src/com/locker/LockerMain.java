package com.locker;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

import javax.xml.bind.DatatypeConverter;


public class LockerMain implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map <String, Holder> credentials = new HashMap<String, Holder>();
	String outFile = "";
	static String passKey = "";
	String mWord = null; 
	
	
	private void storeCredetials(String key, String userName, String password, String seed) throws UnsupportedEncodingException{
		
		if(new File(outFile).exists()){
			retrieveMap();
		}
		Holder h = null;
		if(credentials.get(key) != null){
			h = credentials.get(key);
		}else{
			h = new Holder();
			credentials.put(key, h);
		}
		h.setUserName(userName, seed);
		h.setPassword(password, seed);
		persistMap();
	}
	
	private void getCredetials(String key, String seed) throws UnsupportedEncodingException, Exception{
		
		retrieveMap();
		Holder h = null;
		if(credentials.get(key) != null){
			h = credentials.get(key);
		}else{
			
		}
		String name = h.getUserName(seed);
		String pwd = h.getPassword(seed);
		
		System.out.println("User Name : "+ name);
		System.out.println("Password : "+ pwd);
		
	}
	
	private void raid(String seed){
		
		retrieveMap();
		if(credentials.keySet().size() == 0){
			System.out.println("Nothing to show..");
		}
		
//		System.out.println("-----------------------------------------------------------------------------------------------");
//		System.out.println(""+padUpString("Key", 25)+""+padUpString("User Name", 40)+""+padUpString("Password", 50)+"");
//		System.out.println("-----------------------------------------------------------------------------------------------");
//		
		for(String key : credentials.keySet()){
			
			Holder h = credentials.get(key);
			try{
				
			
			String name = h.getUserName(seed);
			String pwd = h.getPassword(seed);
			System.out.println("==> "+padUpString(key, 40)+padUpString(name, 40)+padUpString(pwd, 40));
			System.out.print("");
			System.out.print("");
//			System.out.println("------------------------");
			}catch(UnsupportedEncodingException e){
			
//				System.out.println("skipped : UnsupportedEncodingException"+key);
//				System.out.println("------------------------");
			
			} catch (Exception e) {
//				System.out.println("skipped : Exception"+key);
//				System.out.println("------------------------");
			}
		
		}
		System.out.println("");
		System.out.println("");
	}
	
	private String padUpString(String rootString, int expectedLength){
		for (int counter = rootString.length(); counter < expectedLength; counter++) {
			rootString = rootString + " ";
	      }
		return rootString;
	}

	private boolean persistMap(){
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(outFile));
			out.writeObject(credentials);
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private boolean retrieveMap(){
		
		try {
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(outFile));  
			credentials = (Map<String, Holder>) in.readObject();  
			in.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		
		Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        
		Scanner scanner = new Scanner(System.in);
		System.out.println("***********************************************************************************************************");
		System.out.println("******   You are are not welcome. To proceed, please prvide your secret word  *****************************");
		System.out.println("***********************************************************************************************************");
		
		String password = new String(c.readPassword("$$> "));
		
		try {
			
			String encPass = DatatypeConverter.printBase64Binary(password.getBytes("UTF-8"));
			if(!encPass.equalsIgnoreCase(passKey)){
				System.out.println("You can't enter..");
				System.exit(0);
			}
			
		} catch (UnsupportedEncodingException e1) {
			System.out.println("You can't enter..");
			System.exit(0);
		}
		
		
		System.out.println("******   You are okay to proceed. Please provide the magic word...            *****************************");
		
		String magicWord = new String(c.readPassword("$$> "));
		
		LockerMain lm = new LockerMain();
		
		do{
			System.out.println("State your command ");
			String command = new String(c.readLine("cmd $ > "));
			String [] cmds = command.split(" ");
			
			
				try {
					if(cmds[0].equalsIgnoreCase("lock")){
						if(cmds.length != 4){
							System.out.println("Incorrect commands format");
						}else{
							lm.storeCredetials(cmds[1], cmds[2], cmds[3], magicWord);
						}
					}else if(cmds[0].equalsIgnoreCase("unlock")){
						if(cmds.length != 2){
							System.out.println("Incorrect commands format");
						}else{
							lm.getCredetials(cmds[1],  magicWord);
						}
					}else if(cmds[0].equalsIgnoreCase("raid")){
						if(cmds.length != 1){
							System.out.println("Incorrect commands format");
						}else{
							lm.raid( magicWord);
						}
					}else if(cmds[0].equalsIgnoreCase("quit")){
						if(cmds.length != 1){
							System.out.println("Incorrect commands format");
						}else{
							System.exit(0);
						}
					}else if(cmds[0].equalsIgnoreCase("help")){
						if(cmds.length != 1){
							System.out.println("Incorrect commands format");
						}else{
							System.out.println("1. lock ");
							System.out.println("2. unlock ");
							System.out.println("3. raid ");
							System.out.println("4. quit ");
							System.out.println("5. help ");
						}
					}else{
						System.out.println("Unknown command");
					}
				} catch (UnsupportedEncodingException e) {
					System.out.println("Failed operation");					
				}
				catch (Exception e) {
					System.out.println("Failed operation");					
				}
			
			
			
		}while(true);
		
		
		

	}

}


class Holder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LockingAlgorith la = null;
	public Holder(){
		la = new LockingAlgorith();
	}
	
	private String userName;
	private String password;
	
	public String getUserName(String seed) throws UnsupportedEncodingException, Exception {
		return la.decryptString(this.userName, seed);
	}
	public void setUserName(String userName, String seed) throws UnsupportedEncodingException {
		this.userName = la.encryptString(userName, seed);
	}
	public String getPassword(String seed) throws UnsupportedEncodingException, Exception {
		return la.decryptString(this.password, seed);
	}
	public void setPassword(String password, String seed) throws UnsupportedEncodingException {
		this.password = la.encryptString(password, seed);
	}
	
	
}
