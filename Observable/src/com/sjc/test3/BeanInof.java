package com.sjc.test3;

public class BeanInof {

	private String name ;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public BeanInof(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "BeanInof [name=" + name + ", age=" + age + "]";
	}
	
}
