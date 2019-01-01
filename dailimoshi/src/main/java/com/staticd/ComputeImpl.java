package com.staticd;

/**
 * 被代理目标类
 * @author Administrator
 *
 */
public class ComputeImpl implements ICompute{

	
	
	public int add(int i, int j) {
		System.out.println("加法");
		return i+j;
	}

	public int subtract(int i, int j) {
		System.out.println("减法");
		return i-j;
	}

	public int multiply(int i, int j) {
		System.out.println("乘法");
		return i*j;
	}

	public int division(int i, int j) {
		System.out.println("初法");
		return i/j;
	}

}
