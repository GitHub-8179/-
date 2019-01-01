package com.sjc.mvc.action;

import javax.servlet.http.HttpServletRequest;

import com.sjc.mvc.Interface.SjcAutower;
import com.sjc.mvc.Interface.SjcController;
import com.sjc.mvc.Interface.SjcRequestMapping;
import com.sjc.mvc.Interface.SjcRuestParment;
import com.sjc.mvc.action.dao.IDao;

@SjcController
@SjcRequestMapping("/test")
public class TestControl {

	@SjcAutower
	private IDao dao;
	
	@SjcAutower("CDaoImpl")
	private IDao CDaoImpl;
	
	@SjcRequestMapping("/login")
	public String login(){
		dao.save();
//		CDaoImpl.save();
		return "";
	}
	
	
	@SjcRequestMapping("/login1")
	public String login1(HttpServletRequest request){
		System.out.println(request.getParameter("name"));
		dao.save();
		return "";
	}
	
	@SjcRequestMapping("/login2")
	public String login2(HttpServletRequest request,@SjcRuestParment("pass")String pass){
		System.out.println(request.getParameter("name"));
		System.out.println(pass);
		dao.save();
		return "";
	}
	
	
	@SjcRequestMapping("/login3")
	public String login3(HttpServletRequest request,@SjcRuestParment("name")String name,@SjcRuestParment("pass")String pass){
		System.out.println(request.getParameter("name"));
		System.out.println(pass);
		dao.save();
		return "";
	}
}
