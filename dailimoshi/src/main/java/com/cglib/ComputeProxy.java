package com.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ComputeProxy implements MethodInterceptor {

	  private Object object;
	  
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("cglib代理开始：");
		long startTime = System.currentTimeMillis();  
        Object result = method.invoke(object, args);
        long stopTime = System.currentTimeMillis();  
        System.out.print("结束代理：耗时：" + (stopTime - startTime) + "毫秒！"); 
        return result;
	}
	 //给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(object.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
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
