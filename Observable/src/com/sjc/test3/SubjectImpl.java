package com.sjc.test3;

import java.util.ArrayList;

public class SubjectImpl implements ISubject{

    private ArrayList<IObserver> array = new ArrayList<IObserver>();


	public void addObserver(IObserver obj) {
		array.add(obj);
	}

	public void deleteObserver(IObserver obj) {
		array.remove(obj);
	}

	public void notifyObserver(BeanInof beanInof) {
		for (IObserver iObserver : array) {
			iObserver.update(this,beanInof);
		}	
	}

}
