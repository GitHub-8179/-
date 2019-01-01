package com.sjc;

import java.util.ArrayList;

public class MyHashMap<K,V> {

	int size ;
	//默认长度
	int defLength = 16;
	//加载因子  链表的加载密度
	float defMiDu =0.75F;
	
	Entry<K,V>[] table ;
	public V put(K k,V v) {
		
		if(table==null) {
			table = new Entry[defLength];
		}
		
		int index = getIndex(k,defLength);
		
		if(table[index]==null) {
			table[index] = new Entry(k,v,null);
			size ++;
		}else {
			
			if(size>=defLength*defMiDu) {
				resize();
			}
			
			Entry<K,V> entry = table[index];
			Entry<K,V> e =  entry ;
			while (e!=null) {
				if(e.getKey().equals(k)) {
					e.setValue(v);
					return v;
				}
				e=e.getNext();
			}
			
			table[index] = new Entry<K,V>(k,v,entry);
			size++;
		}
		
		
		return v;
	}
	
	/**
	 * 扩容
	 */
	private void resize() {
		defLength = defLength<<1;
		Entry<K,V>[] newTable = new Entry[defLength];
		ArrayList<Entry<K,V>> list = new ArrayList<Entry<K,V>>();
		for (Entry<K, V> entry : table) {
			Entry<K,V> e = entry;
			while (e!=null) {
//				int index = getIndex(e.getKey(), defLength);
//				e.setNext(null);
//				newTable[index] = entry;
//				e=e.getNext();
				list.add(e);
				e = e.getNext();
			}
		}
		table = newTable;
		size=0;
		for (Entry<K, V> entry : list) {
			put(entry.getKey(),entry.getValue());
		}
		
	}

	/**
	 * 使用hashcode 和位运算确定数组位置
	 * @param k
	 * @param size
	 * @return
	 */
	private int getIndex(K k,int size) {
		int i = k.hashCode() & (size - 1);
		return i;
	}


	public V get(K k) {
		int index = getIndex(k, defLength);
		if(table[index]!=null) {
			Entry<K,V> entry = table[index];
			Entry<K,V> e =  entry ;
			while (e!=null) {
				if(e.getKey().equals(k)) {
					return e.getValue();
				}
				e=e.getNext();
			}
		}else {
			return null;
		}
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	
	
}
