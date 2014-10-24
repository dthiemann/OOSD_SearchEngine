package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.FieldSearch;
import edu.uiowa.cs2820.engine.Indexer;
import edu.uiowa.cs2820.engine.operators.GreaterThan;

public class IntegrationTests
{
    /*
     * This test checks that we can index data, it gets saved to the database, and that
     * a raw query on the database returns the appropriate data. This does not use FieldSearch yet.
     */
    @Test
    public void test_normal_end_to_end()
    {
        // Create our database
        Database database = new Database();
        
        // Set up our data
        String filename = "test_data.txt";
        String[] lines = {
                "names Tom Dylan Wenbin Nicholas",
                "days Monday Tuesday Wednesday",
                "classes OOP Algorithms ComputerOrg",
                "letters a b c d e f g",
        };
        
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
        
        // Check that all of our data is in the database
        for (int i = 0; i < lines.length; i++)
        {
            String[] lineData = lines[i].split(" ");
            String fieldName = lineData[0];
            for (int k = 1; k < lineData.length; k++)
            {
                Field field = new Field(fieldName, lineData[k]);
                Set<String> identifiers = database.get(field);
                assertTrue(identifiers.contains(filename + " - " + i));
            }
        }
        
        // Now we'll check that field search can access all of this.
        FieldSearch search = new FieldSearch(database);
        
        // Set up a bunch of queries and the expected result
        HashMap<Field, String> searchAndResults = new HashMap<Field, String>();
        searchAndResults.put(new Field("classes", "OOP"), "test_data.txt - 2");
        searchAndResults.put(new Field("names",   "Tom"), "test_data.txt - 0");
        searchAndResults.put(new Field("letters", "a"),   "test_data.txt - 3");
        searchAndResults.put(new Field("days",    "Monday"), "test_data.txt - 1");
        searchAndResults.put(new Field("days",    "OOP"), null);
        
        for (Field field : searchAndResults.keySet())
        {
            String[] results = search.findEquals(field);
            if (searchAndResults.get(field) == null)
                assertEquals(results.length, 0);
            else
                assertEquals(results[0], searchAndResults.get(field));
        }
    }
    
    /*
     * This test checks that we can index data, it gets saved to the database, and that
     * a raw query on the database returns the appropriate data. This does not use FieldSearch yet.
     */
    @Test
    public void test_end_to_end_with_same_fields_in_multiple_files()
    {
        // Create our database
        Database database = new Database();
        
        // Set up our data
        // Notice it is duplicated, so a search on Field("names", "Tom") will return [test_data.txt - 1, test_data.txt - 2]
        String filename = "test_data.txt";
        String[] lines = {
                "names Tom Dylan Wenbin Nicholas",
                "names Tom Dylan Wenbin Nicholas",
                "days Monday Tuesday Wednesday",
                "days Monday Tuesday Wednesday",
                "classes OOP Algorithms ComputerOrg",
                "classes OOP Algorithms ComputerOrg",
                "letters a b c d e f g",
                "letters a b c d e f g",
        };
        
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
        
        // Set up a bunch of queries and the expected result
        HashMap<Field, String[]> searchAndResults = new HashMap<Field, String[]>();
        searchAndResults.put(new Field("classes", "OOP"),   new String[]{"test_data.txt - 4","test_data.txt - 5"});
        searchAndResults.put(new Field("names",   "Tom"),   new String[]{"test_data.txt - 0","test_data.txt - 1"});
        searchAndResults.put(new Field("letters", "a"),     new String[]{"test_data.txt - 6","test_data.txt - 7"});
        searchAndResults.put(new Field("days",    "Monday"),new String[]{"test_data.txt - 2","test_data.txt - 3"});
        searchAndResults.put(new Field("days",    "OOP"),   new String[]{});
        
        for (Field field : searchAndResults.keySet())
        {
            String[] results = search.findEquals(field);
            Arrays.sort(results);
            assertTrue(Arrays.equals(results, searchAndResults.get(field)));
        }
    }
    
    @Test
    public void test_empty_database_search_interaction()
    {
        Database database = new Database();
        FieldSearch search = new FieldSearch(database);
        assertEquals(search.findEquals(new Field("fieldName", "value")).length, 0);
        assertEquals(database.getDatabaseSize(), 0);
    }
    
    @Test
    public void test_uncovered_branches()
    {
        Database database = new Database();
        assertEquals(0, database.getDatabaseSize());
        database.store(new Field("fieldName", "value"), "filename");
        assertEquals(1, database.getDatabaseSize());
        database.store(new Field("fieldName", "value"), null);
        assertEquals(database.getDatabaseSize(), 1);
    }
    
    @Test
    public void test_end_to_end_with_same_fields_in_multiple_files_and_operator_searcg()
    {
        // Create our database
        Database database = new Database();
        
        // Set up our data
        // Notice it is duplicated, so a search on Field("names", "Tom") will return [test_data.txt - 1, test_data.txt - 2]
        String filename = "test_data.txt";
        String[] lines = {
                "names Tom Dylan Wenbin Nicholas",
                "names Tom Dylan Wenbin Nicholas",
                "days Monday Tuesday Wednesday",
                "days Monday Tuesday Wednesday",
                "classes OOP Algorithms ComputerOrg",
                "classes OOP Algorithms ComputerOrg",
                "letters a",
                "letters a b c d e f g",
        };
        
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
        
        // Set up a bunch of queries and the expected result
        HashMap<Field, String[]> searchAndResults = new HashMap<Field, String[]>();
        searchAndResults.put(new Field("classes", "OOP"),   new String[]{"test_data.txt - 4","test_data.txt - 5"});
        searchAndResults.put(new Field("names",   "Tom"),   new String[]{"test_data.txt - 0","test_data.txt - 1"});
        searchAndResults.put(new Field("letters", "a"),     new String[]{"test_data.txt - 6","test_data.txt - 7"});
        searchAndResults.put(new Field("days",    "Monday"),new String[]{"test_data.txt - 2","test_data.txt - 3"});
        searchAndResults.put(new Field("days",    "OOP"),   new String[]{});
        
        for (Field field : searchAndResults.keySet())
        {
            String[] results = search.fieldWithOperator(field, new GreaterThan());
            Arrays.sort(results);
            System.out.println("Searched " + field + ". Got back " + Arrays.toString(results));
            assertTrue(Arrays.equals(results, searchAndResults.get(field)));
        }
    }
}
