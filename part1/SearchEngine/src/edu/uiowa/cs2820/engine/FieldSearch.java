package edu.uiowa.cs2820.engine;

import java.util.HashSet;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

public class FieldSearch {

	private Database db;
	private HashSet<String> found;
	private String[] result;
	
    public FieldSearch(Database database) {
    	this.db = database;
    	
    }
    
    public String[] findEquals(Field search) {
    	found = db.get(search);
    	result = new String[db.getDatabaseSize()];
    	
    	int elem = 0;
    	for (String str: found) {
    		result[elem] = str;
    	}
    	
        return result;
    }

}
