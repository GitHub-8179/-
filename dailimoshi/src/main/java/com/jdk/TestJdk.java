package com.jdk;

import java.lang.reflect.Proxy;

public class TestJdk {
	
	 public static void main(String[] args) throws InterruptedException {
		 ICompute compute = new ComputeImpl();
		 
//		 ICompute computeImpl = (ICompute) Proxy.newProxyInstance(ICompute.class.getClassLoader(), new
//		         Class[]{ICompute.class}, new ComputeProxy(compute));
		 
		 ICompute computeImpl = (ICompute)new ComputeProxy1(compute).getProxyInstance();
		 
		 computeImpl.add(10, 5);
		 computeImpl.division(10, 5);
	 }
	 
}
