package com.sjc.test3;

public class ObserverImpl implements IObserver{


	public void update(ISubject subject, BeanInof info) {
		System.out.println(subject+" –≈œ¢£∫"+info.toString());
		
	}

}
