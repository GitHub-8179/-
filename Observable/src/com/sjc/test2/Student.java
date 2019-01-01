package com.sjc.test2;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者实现
 * @author Administrator
 *
 */
public class Student implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("观察者"+o+"接受到数据:"+arg);
	}

}
