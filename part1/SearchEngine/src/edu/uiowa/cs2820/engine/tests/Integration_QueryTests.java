package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.FieldSourcePair;
import edu.uiowa.cs2820.engine.Indexer;
import edu.uiowa.cs2820.engine.QuerySearch;
import edu.uiowa.cs2820.engine.operators.And;
import edu.uiowa.cs2820.engine.operators.GreaterThan;
import edu.uiowa.cs2820.engine.operators.LessThan;
import edu.uiowa.cs2820.engine.query.DoubleQuery;
import edu.uiowa.cs2820.engine.query.Query;
import edu.uiowa.cs2820.engine.query.Queryable;

public class Integration_QueryTests
{
    private QuerySearch initializeQueryTests()
    {
        // Create our database
        Database database = new Database();

        // Set up our data
        // Notice it is duplicated, so a search on Field("names", "Tom") will
        // return [test_data.txt - 1, test_data.txt - 2]
        String filename = "filename";
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

        QuerySearch search = new QuerySearch(database);
        return search;
    }

    @Test
    public void test_single_query()
    {
        QuerySearch search = initializeQueryTests();

        Field field1 = new Field("letters1", "a");
        Field field2 = new Field("letters1", "b");
        Field field3 = new Field("letters1", "c");
        Field field4 = new Field("letters1", "d");
        Field field5 = new Field("letters1", "e");
        Field field6 = new Field("letters1", "f");
        Field field7 = new Field("letters1", "g");
        // Set up a bunch of queries and the expected result
        Queryable equalsQuery = new Query(field1);
        Queryable lessThanQuery = new Query(field6, new LessThan());
        Queryable greaterThanQuery = new Query(field2, new GreaterThan());
        Queryable simpleCombinedQuery = new DoubleQuery(lessThanQuery, greaterThanQuery, new And()); // should be c, d, e
        
        HashMap<Queryable, FieldSourcePair[]> expectedResults = new HashMap<Queryable, FieldSourcePair[]>();
        expectedResults.put(equalsQuery, new FieldSourcePair[]{new FieldSourcePair(field1, "filename - 0"), new FieldSourcePair(field1, "filename - 1") });
        expectedResults.put(lessThanQuery, new FieldSourcePair[]{new FieldSourcePair(field1, "filename - 0"), new FieldSourcePair(field1, "filename - 1"),
                new FieldSourcePair(field2, "filename - 1"), new FieldSourcePair(field3, "filename - 1"), new FieldSourcePair(field4, "filename - 1"),
                new FieldSourcePair(field5, "filename - 1") });
        expectedResults.put(greaterThanQuery, new FieldSourcePair[]{new FieldSourcePair(field3, "filename - 1"), new FieldSourcePair(field4, "filename - 1"),
                new FieldSourcePair(field5, "filename - 1"), new FieldSourcePair(field6, "filename - 1"), new FieldSourcePair(field7, "filename - 1") });
        expectedResults.put(simpleCombinedQuery, new FieldSourcePair[]{new FieldSourcePair(field3, "filename - 1"), new FieldSourcePair(field4, "filename - 1"),
                new FieldSourcePair(field5, "filename - 1") });
        
        for (Queryable query : expectedResults.keySet())
        {
            FieldSourcePair[] results = search.searchQueryable(query);
            FieldSourcePair[] expected = expectedResults.get(query); 
            Arrays.sort(results);
            Arrays.sort(expected);
            // System.out.println("Query: " + query);
            // System.out.println("Expected: " + Arrays.toString(expected));
            // System.out.println("Actual: " + Arrays.toString(results));
            assertArrayEquals(expected, results);
        }
    }
}
