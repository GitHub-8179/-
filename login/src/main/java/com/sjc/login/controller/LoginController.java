package com.sjc.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sjc.login.service.ILogin;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	
	@Autowired
	private ILogin loginImpl;
	
	@GetMapping("/getToken")
    public Map getData(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashMap();
		try {
			map.put("code", 200);
			map.put("key", UUID.randomUUID());
//			Map dataList = loginImpl.getToken();
		} catch (Exception e) {
			map.put("msg", false);
		}
    	
		return map;
	}

}
