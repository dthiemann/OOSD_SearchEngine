package edu.uiowa.cs2820.engine;

import java.util.HashMap;
import edu.uiowa.cs2820.engine.Field;

public class Database {

	private HashMap<String, Field> map;
	private int count;
	
	public Database() {
		map = new HashMap<String, Field>();
		this.count = 0;
	}
	
	public String getValue(Field key) {
			return map.get(key.getFieldName()).getValue();
	}
	
	public void setValueforKey(Field key) {
		if (key != null) { 
			map.put(key.getFieldName(), key);
			count++;
		}
	}
	
	public int getDatabaseSize() {
		return count;
	}
}
