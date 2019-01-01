package com.cglib;

import java.util.Random;

public class Compute {

	
	public int add(int i, int j) throws InterruptedException {
		System.out.println("加法");
		 Thread.sleep(new Random().nextInt(1000));  
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
