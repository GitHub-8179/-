package com.staticd;

/**
 * ������Ŀ����
 * @author Administrator
 *
 */
public class ComputeImpl implements ICompute{

	
	
	public int add(int i, int j) {
		System.out.println("�ӷ�");
		return i+j;
	}

	public int subtract(int i, int j) {
		System.out.println("����");
		return i-j;
	}

	public int multiply(int i, int j) {
		System.out.println("�˷�");
		return i*j;
	}

	public int division(int i, int j) {
		System.out.println("����");
		return i/j;
	}

}
