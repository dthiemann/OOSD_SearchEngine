package edu.uiowa.cs2820.engine;

import java.util.*;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

public class FieldSearch {

	private Database db;
	
    public FieldSearch(Database database) {
    	this.db = database;
    }
    
    public String[] findEquals(Field search) {
    	List<String> resultList = new ArrayList<String>();
    	String[] result;

    	HashSet<String> found = db.get(search);
    	
       	if (found != null) {
    		for (String str: found) {
    			resultList.add(str);
    		}
       		result = resultList.toArray(new String[resultList.size()]);
    	} else {
    		result = new String[0];
    	}
   	
   		return result;
    }

}
