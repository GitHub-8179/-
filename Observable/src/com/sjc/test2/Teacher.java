package com.sjc.test2;

import java.util.Observable;

public class Teacher extends Observable{

    private String data;

    public String getData() {
        return data;
    }
	  public void setData(String data) {
	        //更新数据
	        this.data = data;
	        System.out.println("主题更新数据："+data);
	        //置更新数据标志
	        setChanged();
	        //通知各个具体的观察者，这里有推数据的作用
	        notifyObservers(data);
	    }
}
