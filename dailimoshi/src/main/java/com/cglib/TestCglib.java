package com.cglib;

public class TestCglib {

	public static void main(String[] args) throws InterruptedException {
		Compute cmpute = new Compute();
		ComputeProxy computeProxy = new ComputeProxy(cmpute);
		Compute computex = (Compute) computeProxy.getProxyInstance();
		computex.add(10, 5);
	}
}
