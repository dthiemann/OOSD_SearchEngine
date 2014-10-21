package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import edu.uiowa.cs2820.engine.Indexer;
import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;

// mock Field class
class FakeField extends Field {
	private String fieldName;
	private String value;

	public FakeField(String fieldName, String value) {
		super(fieldName, value);
		this.fieldName = fieldName;
		this.value = value;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}
	
	@Override
	public String getValue() {
		return value;
	}
}

// mock Database class
class FakeDatabase extends Database {
	private HashMap<Field, Integer> map;
	
	public FakeDatabase() {
		map = new HashMap<Field, Integer>();
	}
	
	@Override
	public void store(Field f, String identifier) {
		if (!map.containsKey(f))
			map.put(f,1);
		map.put(f, map.get(f)+1);
	}
	
	@Override
	public int getDatabaseSize() {
		return map.keySet().size();
	}
}


public class IndexerTest {
	private FakeDatabase db;

    @Before
    public void setUp() throws Exception
    {
    	db = new FakeDatabase();
    }

    @Test
    public void test()
    {

    	Indexer I = new Indexer(db, "FakeIdentifier");
    	assertEquals(db.getDatabaseSize(), 0);

    	FakeField F1 = new FakeField("Name1", "233");
    	I.add(F1);
    	assertEquals(db.getDatabaseSize(), 1);

    	FakeField F2 = new FakeField("Name1", "466");
    	I.add(F2);
    	assertEquals(db.getDatabaseSize(), 1);

        FakeField F3 = new FakeField("Name2", "699");
        I.add(F3);
        assertEquals(db.getDatabaseSize(), 2);

        I.close();
        FakeField F4 = new FakeField("Name2", "000");
        I.add(F4);
        assertEquals(db.getDatabaseSize(), 2);
    }

}
