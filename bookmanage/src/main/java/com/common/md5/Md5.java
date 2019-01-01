package com.common.md5;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

public class Md5 {

	   /**利用MD5进行加密*/
	  public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	    
			StringBuilder sig= new StringBuilder();
				MessageDigest digest = MessageDigest.getInstance("md5");
				byte[] bytes = digest.digest(str.getBytes("utf-8"));
				for (byte b : bytes) {
					String hex = Integer.toHexString(b&0xff);
					if(hex.length()==1){
						sig.append("0"+hex);
					}else{
						sig.append(hex);
					}
				}
				
//		  //确定计算方法
//	    MessageDigest md5=MessageDigest.getInstance("MD5");
//	    BASE64Encoder base64en = new BASE64Encoder();
//	    //加密后的字符串
//	    String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
	    return sig.toString();
	  }
	  
	  
		public static String getClientIP(HttpServletRequest httpservletrequest) {
			if (httpservletrequest == null)
				return null;
			String s = httpservletrequest.getHeader("X-Forwarded-For");
			if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
				s = httpservletrequest.getHeader("Proxy-Client-IP");
			if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
				s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
			if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
				s = httpservletrequest.getHeader("HTTP_CLIENT_IP");
			if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
				s = httpservletrequest.getHeader("HTTP_X_FORWARDED_FOR");
			if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
				s = httpservletrequest.getRemoteAddr();
			if ("127.0.0.1".equals(s) || "0:0:0:0:0:0:0:1".equals(s))
				try {
					s = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException unknownhostexception) {
				}
			return s;
		}
}
