package com.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ComputeProxy implements InvocationHandler{

	  private Object object;
	  
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
			System.out.println("代理开始：");
			long startTime = System.currentTimeMillis();  
	        Object result = method.invoke(object, args);
	        long stopTime = System.currentTimeMillis();  
	        System.out.print("结束代理：耗时：" + (stopTime - startTime) + "毫秒！"); 
		  return result;
	}
	
	

	public ComputeProxy(Object object) {
		super();
		this.object = object;
	}

	public ComputeProxy() {
		super();
	}
	
	

}
