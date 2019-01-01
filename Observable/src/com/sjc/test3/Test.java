package com.sjc.test3;

public class Test {

	public static void main(String[] args) {
		SubjectImpl s = new SubjectImpl();
		ObserverImpl o = new ObserverImpl();
		ObserverImpl o1 = new ObserverImpl();

		s.addObserver(o);
		s.addObserver(o1);
		s.notifyObserver(new BeanInof("test", "10"));
	}
}
