package com.staticd;

public class Test {

	public static void main(String[] args) {
		ICompute computeImpl = new ComputeImpl();
		
		ComputeProxy computeProxy = new ComputeProxy(computeImpl);
		System.out.println("≤‚ ‘£∫"+computeProxy.add(10, 5));
	}
}
