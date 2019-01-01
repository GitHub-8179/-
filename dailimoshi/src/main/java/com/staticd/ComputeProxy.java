package com.staticd;

public class ComputeProxy implements ICompute {

	private ICompute computeImpl;
	
	public int add(int i, int j) {
		System.out.println("代理加法");
		computeImpl.add(i, j);
		return i+j;
	}

	public int subtract(int i, int j) {
		System.out.println("代理减法");
		computeImpl.subtract(i, j);
		return i-j;
	}

	public int multiply(int i, int j) {
		System.out.println("代理乘法");
		computeImpl.multiply(i, j);
		return i*j;
	}

	public int division(int i, int j) {
		System.out.println("代理初法");
		computeImpl.division(i, j);
		return i/j;
	}


	public ComputeProxy(ICompute computeImpl) {
		super();
		this.computeImpl = computeImpl;
	}

	public ComputeProxy() {
		super();
	}

}
