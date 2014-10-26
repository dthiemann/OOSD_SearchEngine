package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.FieldSearch;
import edu.uiowa.cs2820.engine.FieldSourcePair;
import edu.uiowa.cs2820.engine.Indexer;
import edu.uiowa.cs2820.engine.operators.Equals;
import edu.uiowa.cs2820.engine.operators.GreaterThan;
import edu.uiowa.cs2820.engine.operators.GreaterThanEqualTo;
import edu.uiowa.cs2820.engine.operators.LessThan;
import edu.uiowa.cs2820.engine.operators.LessThanEqualTo;
import edu.uiowa.cs2820.engine.operators.Not;

public class Integration_OperatorTests
{

    private FieldSearch initializeOperatorTests()
    {
        // Create our database
        Database database = new Database();

        // Set up our data
        // Notice it is duplicated, so a search on Field("names", "Tom") will
        // return [test_data.txt - 1, test_data.txt - 2]
        String filename = "identifier";
        String[] lines = { 
                "letters1 a", 
                "letters1 a b c d e f g", 
                "letters2 a b c d e f g", 
                "letters2 a",
                "notTest1 a b",
                "notTest2 c",};

        // Load our data
        int lineNum = 0;
        for (String line : lines)
        {
            Indexer indexer = new Indexer(database, filename + " - " + lineNum);
            String[] lineData = line.split(" ");
            String fieldName = lineData[0];
            for (int i = 1; i < lineData.length; i++)
                indexer.add(new Field(fieldName, lineData[i]));
            lineNum++;
        }

        // Now we'll check that field search can access all of this.
        FieldSearch search = new FieldSearch(database);
        return search;
    }

    @Test
    public void test_search_with_not_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("notTest1", "a");
        Field field2 = new Field("notTest1", "b");
        Field field3 = new Field("notTest2", "c");
        HashMap<Field, FieldSourcePair[]> searchNotResults = new HashMap<Field, FieldSourcePair[]>();
        searchNotResults.put(field1, new FieldSourcePair[] { new FieldSourcePair(field2, "identifier - 4") });
        searchNotResults.put(field2, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 4") });
        searchNotResults.put(field3, new FieldSourcePair[] {  });

        for (Field field : searchNotResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new Not());
            FieldSourcePair[] expected = searchNotResults.get(field); 
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

    @Test
    public void test_search_with_greater_than_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("letters1", "f");
        Field field2 = new Field("letters1", "g");
        Field field3 = new Field("letters2", "f");
        Field field4 = new Field("letters2", "g");
        HashMap<Field, FieldSourcePair[]> searchGreaterThanResults = new HashMap<Field, FieldSourcePair[]>();
        searchGreaterThanResults.put(field1, new FieldSourcePair[] { new FieldSourcePair(field2, "identifier - 1") });
        searchGreaterThanResults.put(field2, new FieldSourcePair[] {  });
        searchGreaterThanResults.put(field3, new FieldSourcePair[] { new FieldSourcePair(field4, "identifier - 2") });
        searchGreaterThanResults.put(field4, new FieldSourcePair[] {  });

        for (Field field : searchGreaterThanResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new GreaterThan());
            FieldSourcePair[] expected = searchGreaterThanResults.get(field); 
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

    @Test
    public void test_search_with_greater_or_equal_to_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("letters1", "f");
        Field field2 = new Field("letters1", "g");
        Field field3 = new Field("letters2", "f");
        Field field4 = new Field("letters2", "g");
        HashMap<Field, FieldSourcePair[]> searchGreaterThanEqToResults = new HashMap<Field, FieldSourcePair[]>();
        searchGreaterThanEqToResults.put(field1, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 1"), new FieldSourcePair(field2, "identifier - 1") });
        searchGreaterThanEqToResults.put(field2, new FieldSourcePair[] { new FieldSourcePair(field2, "identifier - 1") });
        searchGreaterThanEqToResults.put(field3, new FieldSourcePair[] { new FieldSourcePair(field3, "identifier - 2"), new FieldSourcePair(field4, "identifier - 2") });
        searchGreaterThanEqToResults.put(field4, new FieldSourcePair[] { new FieldSourcePair(field4, "identifier - 2") });

        for (Field field : searchGreaterThanEqToResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new GreaterThanEqualTo());
            FieldSourcePair[] expected = searchGreaterThanEqToResults.get(field); 
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

    @Test
    public void test_search_with_less_than_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("letters1", "a");
        Field field2 = new Field("letters1", "b");
        Field field3 = new Field("letters2", "a");
        Field field4 = new Field("letters2", "b");
        Field field5 = new Field("letters2", "c");
        HashMap<Field, FieldSourcePair[]> searchLessThanResults = new HashMap<Field, FieldSourcePair[]>();
        searchLessThanResults.put(field1, new FieldSourcePair[] {});
        searchLessThanResults.put(field2, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 0"), new FieldSourcePair(field1, "identifier - 1") });
        searchLessThanResults.put(field3, new FieldSourcePair[] {});
        searchLessThanResults.put(field5, new FieldSourcePair[] { new FieldSourcePair(field4, "identifier - 2"), new FieldSourcePair(field3, "identifier - 2"),
                new FieldSourcePair(field3, "identifier - 3") });

        for (Field field : searchLessThanResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new LessThan());
            FieldSourcePair[] expected = searchLessThanResults.get(field);
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

    @Test
    public void test_search_with_less_or_equal_to_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("letters1", "a");
        Field field2 = new Field("letters1", "b");
        Field field3 = new Field("letters2", "a");
        Field field4 = new Field("letters2", "b");
        Field field5 = new Field("letters2", "c");
        HashMap<Field, FieldSourcePair[]> searchLessThanResults = new HashMap<Field, FieldSourcePair[]>();
        searchLessThanResults.put(field1, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 0"), new FieldSourcePair(field1, "identifier - 1")});
        searchLessThanResults.put(field2, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 0"), new FieldSourcePair(field1, "identifier - 1"), new FieldSourcePair(field2, "identifier - 1") });
        searchLessThanResults.put(field3, new FieldSourcePair[] { new FieldSourcePair(field3, "identifier - 2"), new FieldSourcePair(field3, "identifier - 3")});
        searchLessThanResults.put(field5, new FieldSourcePair[] { new FieldSourcePair(field5, "identifier - 2"), new FieldSourcePair(field4, "identifier - 2"), new FieldSourcePair(field3, "identifier - 2"),
                new FieldSourcePair(field3, "identifier - 3") });

        for (Field field : searchLessThanResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new LessThanEqualTo());
            FieldSourcePair[] expected = searchLessThanResults.get(field);
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

    @Test
    public void test_search_with_equals_operator()
    {
        FieldSearch search = initializeOperatorTests();

        // Set up a bunch of queries and the expected result
        Field field1 = new Field("letters1", "a");
        Field field2 = new Field("letters1", "b");
        Field field3 = new Field("letters2", "a");
        Field field4 = new Field("letters2", "c");
        Field field5 = new Field("letters3", "a"); // Not in database
        HashMap<Field, FieldSourcePair[]> searchEqualsResults = new HashMap<Field, FieldSourcePair[]>();
        searchEqualsResults.put(field1, new FieldSourcePair[] { new FieldSourcePair(field1, "identifier - 0"), new FieldSourcePair(field1, "identifier - 1") });
        searchEqualsResults.put(field2, new FieldSourcePair[] { new FieldSourcePair(field2, "identifier - 1") });
        searchEqualsResults.put(field3, new FieldSourcePair[] { new FieldSourcePair(field3, "identifier - 2"), new FieldSourcePair(field3, "identifier - 3") });
        searchEqualsResults.put(field4, new FieldSourcePair[] { new FieldSourcePair(field4, "identifier - 2") });
        searchEqualsResults.put(field5, new FieldSourcePair[] {  });

        for (Field field : searchEqualsResults.keySet())
        {
            FieldSourcePair[] results = search.fieldWithOperator(field, new Equals());
            FieldSourcePair[] expected = searchEqualsResults.get(field);
            Arrays.sort(results);
            Arrays.sort(expected);
            assertArrayEquals(expected, results);
        }
    }

}
