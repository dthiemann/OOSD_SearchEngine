package edu.uiowa.cs2820.engine;

import java.util.HashMap;

public class Database {

	private HashMap<String, String> map;
	private int count;
	
	public Database() {
		map = new HashMap<String, String>();
		this.count = 0;
	}
	
	public String getValue(String key) {
			return map.get(key);
	}
	
	public void setValueforKey(String key, String value) {
		if (key != null) { 
			map.put(key, value);
			count++;
		}
	}
	
	public int getDatabaseSize() {
		return count;
	}
	
}
