package edu.uiowa.cs2820.engine.tests;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Indexer;

public class IntegrationTests
{
    @Test
    public void test_indexing_filesearch_exchange()
    {
        // We have several op
        
        // This comes from the UserDemo.java file.
        // It currently assumes that the constructor, add method, and close
        // method as seen in UserDemo.java are operational in Indexer.java
        String fieldName = "name";
        String[] values = { "Tom", "Dylan", "Nicholas", "Wenbin" };
        String identifier = "names.txt";
        //Indexer indexer = new Indexer(identifier);

        for (String value : values)
        {
            // Create the field for all the values and add them to the indexer
            Field field = new Field(fieldName, value);
            //indexer.add(field);
        }

        // Close the indexer, presumably this means it stops accepting input
        //indexer.close();
        
        
    }

}
