package edu.uiowa.cs2820.engine;

import java.util.*;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

public class FieldSearch {

	private Database db;
	private HashSet<String> found;
	private List<String> resultList = new ArrayList<String>();
	private String[] result;
	
    public FieldSearch(Database database) {
    	this.db = database;
    }
    
    public String[] findEquals(Field search) {
    	found = db.get(search);
    	
    	//May need an if to check for empty 'found'
    	for (String str: found) {
   			resultList.add(str);
   		}
   	
   		result = resultList.toArray(new String[resultList.size()]);
   		
   		return result;
    }

}
