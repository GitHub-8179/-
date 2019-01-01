package com.hims.login.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hims.login.dao.ILoginUserDao;

@Service
public class LoginUserImpl implements ILoginUser{

	@Resource
	private ILoginUserDao loginUserDao;
	
	public List loginVerify(String user, String pass) {
		// TODO Auto-generated method stub
		return loginUserDao.loginVerify(user, pass);
//		return null;
	}

	public boolean save(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	public List selUserInfor(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
