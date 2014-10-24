package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.FieldSearch;

public class FieldSearchTest {
	
	private Database db;
	
	@Before
	public void setUp() throws Exception {
		db = new Database();
	}
	
	@Test
	public void test_FieldSearch_and_findEquals() {
		// The database needs a few things to be able to find
		Field field1 = new Field("name", "namevalue");
		Field field2 = new Field("field", "fieldvalue");
		Field field3 = new Field("field", "fieldvalue_too");
		
		db.store(field1, "testFile.txt");
		db.store(field2, "aFile.txt");
		db.store(field2, "anotherFile.txt");
		db.store(field3, "aFile.txt");

		
		HashSet<String> set1 = new HashSet<String>();
		set1.add("testFile.txt");
		HashSet<String> set2 = new HashSet<String>();
		set2.add("aFile.txt");
		HashSet<String> set3 = new HashSet<String>();
		set3.add("anotherFile.txt");
				
		
		
		// Search for the only match in the database
		FieldSearch S = new FieldSearch(db);
		String[] r1 = S.findEquals(new Field("name", "namevalue"));
		assertEquals(r1.length, 1);
		assertEquals(r1[0], "testFile.txt");

		// Search for something that has two matches
		S = new FieldSearch(db);
		String[] r2 = S.findEquals(new Field("field", "fieldvalue"));
		assertEquals(r2.length, 2);
		assertArrayEquals(r2, new String[]{"anotherFile.txt", "aFile.txt"});

		
	}

}
