package com.staticd;

public class ComputeProxy implements ICompute {

	private ICompute computeImpl;
	
	public int add(int i, int j) {
		System.out.println("����ӷ�");
		computeImpl.add(i, j);
		return i+j;
	}

	public int subtract(int i, int j) {
		System.out.println("�������");
		computeImpl.subtract(i, j);
		return i-j;
	}

	public int multiply(int i, int j) {
		System.out.println("����˷�");
		computeImpl.multiply(i, j);
		return i*j;
	}

	public int division(int i, int j) {
		System.out.println("�������");
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
