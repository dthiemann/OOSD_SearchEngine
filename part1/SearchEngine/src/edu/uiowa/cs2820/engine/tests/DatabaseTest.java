package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.*;
import java.util.*;
import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import org.junit.Before;
import org.junit.Test;


public class DatabaseTest {
	
	
    @Before
    public void setUp() throws Exception
    {
    }
    
    @Test
    public void testPutAndGet() {
    	Database db = new Database();
    	Field testField = new Field("name", "value");
    	Field testField2 = new Field("name2", "value2");
    	
    	db.store(testField, "TempFile.txt");
    	db.store(testField2, "SecondTempFile.txt");
    	
    	HashSet<String> mySet1 = new HashSet<String>();
    	mySet1.add("TempFile.txt");
    	HashSet<String> mySet2 = new HashSet<String>();
    	mySet2.add("SecondTempFile.txt");
    	
    	assertEquals(db.get(testField), mySet1);
    	assertEquals(db.get(testField2), mySet2);
    }
}
