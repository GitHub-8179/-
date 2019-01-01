package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ComputeProxy implements MethodInterceptor {

	  private Object object;
	  
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("cglib����ʼ��");
		long startTime = System.currentTimeMillis();  
        Object result = method.invoke(object, args);
        long stopTime = System.currentTimeMillis();  
        System.out.print("����������ʱ��" + (stopTime - startTime) + "���룡"); 
        return result;
	}
	 //��Ŀ����󴴽�һ���������
    public Object getProxyInstance(){
        //1.������
        Enhancer en = new Enhancer();
        //2.���ø���
        en.setSuperclass(object.getClass());
        //3.���ûص�����
        en.setCallback(this);
        //4.��������(�������)
        return en.create();

    }
	public ComputeProxy(Object object) {
		super();
		this.object = object;
	}
	public ComputeProxy() {
		super();
	}


}
