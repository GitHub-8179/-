package com.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ComputeProxy1 {

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
	
	

	public ComputeProxy1(Object object) {
		super();
		this.object = object;
	}

	public ComputeProxy1() {
		super();
	}
	
	
	//��Ŀ��������ɴ������
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
        		object.getClass().getClassLoader(),
        		object.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("��ʼ����2");
                        //ִ��Ŀ����󷽷�
                        Object returnValue = method.invoke(object, args);
                        System.out.println("�ύ����2");
                        return returnValue;
                    }
                }
        );
    }

}
