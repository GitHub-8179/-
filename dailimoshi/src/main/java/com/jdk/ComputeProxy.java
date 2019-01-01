package com.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ComputeProxy implements InvocationHandler{

	  private Object object;
	  
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
			System.out.println("����ʼ��");
			long startTime = System.currentTimeMillis();  
	        Object result = method.invoke(object, args);
	        long stopTime = System.currentTimeMillis();  
	        System.out.print("����������ʱ��" + (stopTime - startTime) + "���룡"); 
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
