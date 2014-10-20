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
    
    // Yeah I wish he would of given us a little more direction with this assignment
    // - Dylan

    // As far as I can see, the field is just a pair containing the fieldName and some value.
    // Having this data structure may allow us to do different operations on different types
    // of values under the same field. For example, convert the string "$45.5" to a float while
    // simply store "(tax included)". - Wenbin
    
    @Test
    public void testKeyValueSetAndGet() {
    	fail("Implement new tests"); 
    }
}
