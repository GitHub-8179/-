package com.sjc.test2;

import java.util.Observable;
import java.util.Observer;

/**
 * �۲���ʵ��
 * @author Administrator
 *
 */
public class Student implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("�۲���"+o+"���ܵ�����:"+arg);
	}

}
