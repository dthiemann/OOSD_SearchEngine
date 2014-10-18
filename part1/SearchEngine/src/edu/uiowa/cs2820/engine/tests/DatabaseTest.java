package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.*;
import edu.uiowa.cs2820.engine.Database;

import org.junit.Before;
import org.junit.Test;

public class DatabaseTest {
	
    @Before
    public void setUp() throws Exception
    {
    }
    
    // What is the purpose of the field class if we just use strings as the keys and values?
    // I wasn't really clear on why he had us even make the field class. - Tom
    @Test
    public void testKeyValueSetAndGet() {
    	String key1 = "this is my key";
    	String value1 = "this is my value";
    	String key2 = "key2";
    	String value2 = "value2";
    	
    	Database myDB = new Database();
    	myDB.setValueforKey(key1, value1);
    	myDB.setValueforKey(key2, value2);

    	assertEquals(myDB.getValue(key1), value1);
    	assertEquals(myDB.getValue(key2), value2);
    }
}
