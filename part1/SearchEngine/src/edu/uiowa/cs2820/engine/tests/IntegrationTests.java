package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Database;
import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.FieldSearch;
import edu.uiowa.cs2820.engine.Indexer;

public class IntegrationTests
{
    private void setupNamesIndex(Database database)
    {
        // This comes from the UserDemo.java file.
        // It currently assumes that the constructor, add method, and close
        // method as seen in UserDemo.java are operational in Indexer.java
        String fieldName = "name";
        String[] values = { "Tom", "Dylan", "Nicholas", "Wenbin" };
        String identifier = "names.txt";

        // The indexer seems to take a series of fields. They don't all have
        // to have the same fieldName. All being in the same indexer just means
        // they come from the same identifier
        Indexer indexer = new Indexer(database, identifier);

        for (String value : values)
        {
            // Create the field for all the values and add them to the indexer
            Field field = new Field(fieldName, value);
            indexer.add(field);
        }

        // Close the indexer
        indexer.close();
    }

    private void setupClassesIndex(Database database)
    {
        // This comes from the UserDemo.java file.
        // It currently assumes that the constructor, add method, and close
        // method as seen in UserDemo.java are operational in Indexer.java
        String fieldName = "class";
        String[] values = { "OOP", "CSII", "Algorithms", "Computer Org" };
        String identifier1 = "classes.txt";
        String identifier2 = "classes2.txt";

        // The indexer seems to take a series of fields. They don't all have
        // to have the same fieldName. All being in the same indexer just means
        // they come from the same identifier
        Indexer indexer1 = new Indexer(database, identifier1);
        Indexer indexer2 = new Indexer(database, identifier2);

        for (String value : values)
        {
            // Create the field for all the values and add them to the indexer
            Field field = new Field(fieldName, value);
            indexer1.add(field);
            indexer2.add(field);
        }

        // Close the indexer
        indexer1.close();
        indexer2.close();
    }

    @Test
    public void broad_test()
    {
        Database database = new Database();
        setupNamesIndex(database);
        setupClassesIndex(database);

        FieldSearch search = new FieldSearch(database);
        ArrayList<Field> searches = new ArrayList<Field>();
        searches.add(new Field("name", "Tom"));
        searches.add(new Field("class", "OOP"));

        for (Field f : searches)
        {
            System.out.println("Looking for field:" + f.getFieldName() + " with value:" + f.getValue());
            String[] result = search.findEquals(f);
            for (String identifier : result)
            {
                System.out.println("\tWe found it in: " + identifier);
            }
        }
    }

    /*
     * This test checks that we can index data, it gets saved to the database, and that
     * a raw query on the database returns the appropriate data. This does not use FieldSearch yet.
     */
    @Test
    public void test_end_to_end() throws FileNotFoundException
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
                Set<String> identifiers = database.getValue(field);
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
}
