package com.hims.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

public interface ILoginUser {
	public List loginVerify(String user,String pass);
	
	public boolean save(HttpServletRequest request ) throws Exception ;
	
	public boolean update(HttpServletRequest request );
	
	public boolean delete(HttpServletRequest request );
	
	public List selUserInfor(HttpServletRequest request );
}
