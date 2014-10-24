package edu.uiowa.cs2820.engine;

import java.util.*;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.Operator;

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

    public HashSet<String> getWithOperator(Field search, Operator operator)
    {
        if (map.containsKey(search))
        {
            HashSet<String> results = new HashSet<String>();
            for (Field field : map.keySet())
            {
                if (operator.evaluate(field, search) && field.getFieldName().equals(search.getFieldName()))
                    results.addAll(map.get(field));
            }
            return results;
        }
        else
        {
            return null;
        }
    }
}
