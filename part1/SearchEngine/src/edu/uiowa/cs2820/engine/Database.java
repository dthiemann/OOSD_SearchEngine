package edu.uiowa.cs2820.engine;

import java.util.*;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.Operator;
import edu.uiowa.cs2820.engine.query.Queryable;

/*
 * Database used by the Indexer and FieldSearch classes for storing
 * and retrieving indexed data.
 * 
 * author: Dylan Thiemann
 * date: 10/23/14
 * 
 */

public class Database
{

    private HashMap<Field, HashSet<String>> map;
    private int count;

    public Database()
    {
        map = new HashMap<Field, HashSet<String>>();
        this.count = 0;
    }

    public HashSet<String> get(Field field)
    {
        if (map.containsKey(field))
            return map.get(field);
        else
            return null;
    }

    public void store(Field field, String fileName)
    {
        if (map.containsKey(field) && fileName != null)
        {
            map.get(field).add(fileName);
            count++;
        }
        else if (fileName != null)
        {
            HashSet<String> newSet = new HashSet<String>();
            newSet.add(fileName);
            map.put(field, newSet);
            count++;
        }
    }

    public int getDatabaseSize()
    {
        return count;
    }

    public HashMap<Field, HashSet<String>> getWithOperator(Field search, Operator<Field> operator)
    {
        if (map.containsKey(search))
        {
            HashMap<Field, HashSet<String>> results = new HashMap<Field, HashSet<String>>();

            // Loop through the database
            for (Field field : map.keySet())
            {
                // If a field matches our criteria, add it and all the strings
                // it maps to to our result
                if (operator.evaluate(field, search) && field.getFieldName().equals(search.getFieldName()))
                {
                    results.put(field, map.get(field));
                }
            }
            return results;
        }
        else
        {
            return null;
        }
    }

    public HashMap<Field, HashSet<String>> searchQueryable(Queryable query)
    {
        HashMap<Field, HashSet<String>> result = new HashMap<Field, HashSet<String>>();
        for (Field key : map.keySet())
        {
            if (query.isSatisfiedBy(key))
            {
                result.put(key, map.get(key));
            }
        }

        return result;
    }
}
