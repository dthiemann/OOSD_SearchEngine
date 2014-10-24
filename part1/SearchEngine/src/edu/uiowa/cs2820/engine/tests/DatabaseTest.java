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
    
    // Checks to see that we are able to retrieve the objects
    // with the same keys we stored them with
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
    
    // Tests to see if we can add multiple values to a value of
    // a single key
    @Test
    public void testAddingMultipleValuesOneKey() {
    	Database db = new Database();
    	Field testField = new Field("name", "value");
    	Field testField2 = new Field("name", "value");
    	
    	db.store(testField, "tempFile.txt");
    	db.store(testField2, "tempFile2.txt");
    	
    	HashSet<String> mySet1 = new HashSet<String>();
    	mySet1.add("tempFile.txt");
    	mySet1.add("tempFile2.txt");
    	
    	assertEquals(db.get(testField), mySet1);
    }
    
    // If we try to 'get' with a Field that is not in the database,
    // we return a null
    @Test
    public void testKeyNotInDB() {
    	Database db = new Database();
    	Field testField = new Field("name", "value");
    	Field testField2 = new Field("name2", "value");
    	
    	db.store(testField, "file.txt");
    	
    	assertEquals(db.get(testField2), null);
    }
    
    // Test to see that we don't add null values into our 
    // database, if we have a null value, we simply don't 
    // add it to the DB
    @Test
    public void testValueIsNull() {
    	Database db = new Database();
    	Field testField = new Field("name", "value");
    	
    	db.store(testField, null);
    	
    	assertEquals(db.get(testField), null);
    }
    
    // Test to see that we don't add null values into our 
    // database, if we have a null value, we simply don't 
    // add it to the already existing key-value pair
    @Test
    public void testValueIsNullWithOtherValues() {
    	Database db = new Database();
    	Field testField = new Field("name", "value");
    	
    	db.store(testField, "file.txt");
    	db.store(testField, null);
    	
    	HashSet<String> mySet = new HashSet<String>();
    	mySet.add("file.txt");
    	
    	assertEquals(db.get(testField), mySet);
    }
    
}
