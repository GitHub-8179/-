package com.jdk;

public interface ICompute {
	/**
	 * 加
	 * @param i
	 * @param j
	 * @return
	 * @throws InterruptedException 
	 */
	int add(int i,int j) throws InterruptedException;
	/**
	 * 减法
	 * @param i
	 * @param j
	 * @return
	 */
	int subtract(int i,int j);
	/**
	 * 乘法
	 * @param i
	 * @param j
	 * @return
	 */
	int multiply(int i,int j);
	/**
	 * 除法
	 * @param i
	 * @param j
	 * @return
	 */
	int division(int i,int j);

}
