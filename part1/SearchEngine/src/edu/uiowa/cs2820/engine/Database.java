package edu.uiowa.cs2820.engine;

import java.util.*;

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

// So it seems like that each field is going to have multiple values (according
// to the UserDemo.java). So I think it would be best to use an ArrayList for the
// values of each key so we can store all the values. - Dylan

// Maybe I'll create a hashmap of hashmaps and use the index to access 
// the first layer of hashmaps and then use the field for taht index to 
// get at the rest of the data (then we could use a singleton) - Dylan

// Guys, the professor said that this class "should not use any files" on his
// webpage. Besides, what I understand is that it is the Indexer.close()'s
// responsibility to pass fieldName, identifier and the list of values to the
// Database class. You can see my code for details, I mocked a D.store(...) method.
// Maybe we should discuss this in the Github's Issue section.
// - Wenbin

// Yeah we were never planning on use files. We were just trying to figure
// out the best way to utilize the class. I posted an issue about it on the
// Github page. - Dylan


public class Database {

	
	private HashMap<Field, HashSet<String>> map;
	private int count;
	
	public Database() {
		map = new HashMap<Field, HashSet<String>>();
		this.count = 0;
	}
	
	public HashSet<String> get(Field field) {
		return map.get(field);
	}
	
	public void store(Field field, String fileName) {
		if (map.containsKey(field)) {
			map.get(field).add(fileName);
		} else {
			HashSet<String> newSet = new HashSet<String>();
			newSet.add(fileName);
			map.put(field, newSet);
		}
	}
	
	public int getDatabaseSize() {
		return count;
	}
}
