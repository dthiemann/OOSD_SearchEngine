package edu.uiowa.cs2820.engine.tests;

import java.util.ArrayList;

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
        String identifier = "classes.txt";

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

    @Test
    public void broad_test()
    {
        Database database = new Database();
        setupNamesIndex(database);
        setupClassesIndex(database);

        FieldSearch search = new FieldSearch();
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

}
