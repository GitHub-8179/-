package com.jdk;

public interface ICompute {
	/**
	 * ��
	 * @param i
	 * @param j
	 * @return
	 * @throws InterruptedException 
	 */
	int add(int i,int j) throws InterruptedException;
	/**
	 * ����
	 * @param i
	 * @param j
	 * @return
	 */
	int subtract(int i,int j);
	/**
	 * �˷�
	 * @param i
	 * @param j
	 * @return
	 */
	int multiply(int i,int j);
	/**
	 * ����
	 * @param i
	 * @param j
	 * @return
	 */
	int division(int i,int j);

}
