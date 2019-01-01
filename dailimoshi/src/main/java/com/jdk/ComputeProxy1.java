package com.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ComputeProxy1 {

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
	
	

	public ComputeProxy1(Object object) {
		super();
		this.object = object;
	}

	public ComputeProxy1() {
		super();
	}
	
	
	//给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
        		object.getClass().getClassLoader(),
        		object.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务2");
                        //执行目标对象方法
                        Object returnValue = method.invoke(object, args);
                        System.out.println("提交事务2");
                        return returnValue;
                    }
                }
        );
    }

}
