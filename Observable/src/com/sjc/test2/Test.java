package com.sjc.test2;

public class Test {

	public static void main(String[] args) {
		Teacher t = new Teacher();
		Student s = new Student();
		
		t.addObserver(s);
		t.setData("≤‚ ‘");
//		t.notifyObservers(s);
//		t.notify();
	}
}
