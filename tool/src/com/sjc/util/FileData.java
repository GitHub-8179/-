package com.sjc.util;

import java.io.IOException; 
import java.io.InputStream; 
import java.util.Map;
import java.util.Properties; 

public class FileData {
	
	public static Map getFileDate(){
		FileData loadProp = new FileData(); 
		  InputStream in = loadProp.getClass().getResourceAsStream("/a.properties"); 
		  Properties prop = new Properties(); 
		  try {
		   prop.load(in); 
		  } catch (IOException e) { 
		   e.printStackTrace(); 
		  } 
//		  System.out.println(prop.getProperty("name")); 
//		  System.out.println(prop.getProperty("age"));
		  
		return prop;
	}
	}
