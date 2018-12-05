package com.sjc.msm;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 秒滴科技短信发送
 * （梦网云通讯）
 * @author Administrator
 *
 */
//@WebService(portName="sendSMS")
//@WebService(name="sendSMS")
public class SendMsm extends HttpServlet{

	/**
	 * �̳�HttpServlet
	 * web.xml����
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String phone = req.getParameter("phone");
//		String yzm = GetYzm.getCode(phone);
		String yzm="";
		System.out.println(yzm+"vvvvvvvvvvvvvvvvvvvv");
		resp.getWriter().print(yzm);
		
		
	}

	
}
