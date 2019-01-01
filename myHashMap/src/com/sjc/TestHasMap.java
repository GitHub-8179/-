package com.sjc;

public class TestHasMap {

	public static void main(String[] args) {
		
		MyHashMap<String, String> map = new MyHashMap<>();
		
		for (int i = 0; i < 100; i++) {
			map.put(""+i,""+i);
			
		}
		map.put(""+2,""+22);
		map.put(""+2,""+242);

		map.put(""+22,""+222);
		map.put(""+3,""+33);


		System.out.println(map.getSize());
		
		for (int i = 0; i < 100; i++) {
			System.out.println(map.get(i+""));
			
		}
	}
}
