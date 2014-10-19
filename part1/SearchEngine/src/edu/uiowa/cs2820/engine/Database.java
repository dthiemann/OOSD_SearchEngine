package edu.uiowa.cs2820.engine;

import java.util.HashMap;
import edu.uiowa.cs2820.engine.Field;

// There is a definitely a better way to implement this
// I just don't know what it is yet

// Would a singleton make more sense? That way we know there is only
// one instance of a database ever. I'm not sure if thats considered
// bad practice though. I think I read something about it not being
// ideal for TDD - Tom

// That's what I was thinking at first... but wouldn't we need a database instance 
// for each index? - Dylan

// I don't think so. If each index just did something like 
//      Database.getInstance().setValueforKey(field)
// then they'd all end up in the same database, provided we were in the same session.
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
