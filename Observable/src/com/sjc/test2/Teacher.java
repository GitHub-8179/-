package com.sjc.test2;

import java.util.Observable;

public class Teacher extends Observable{

    private String data;

    public String getData() {
        return data;
    }
	  public void setData(String data) {
	        //��������
	        this.data = data;
	        System.out.println("����������ݣ�"+data);
	        //�ø������ݱ�־
	        setChanged();
	        //֪ͨ��������Ĺ۲��ߣ������������ݵ�����
	        notifyObservers(data);
	    }
}
