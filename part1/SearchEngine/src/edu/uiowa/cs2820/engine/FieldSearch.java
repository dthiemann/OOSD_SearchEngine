package edu.uiowa.cs2820.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import edu.uiowa.cs2820.engine.operators.Operator;

public class FieldSearch
{

    protected Database db;

    public FieldSearch(Database database)
    {
        this.db = database;
    }

    public String[] findEquals(Field search)
    {
        List<String> resultList = new ArrayList<String>();
        String[] result;

        HashSet<String> found = db.get(search);

        if (found != null)
        {
            for (String str : found)
            {
                resultList.add(str);
            }
            result = resultList.toArray(new String[resultList.size()]);
        }
        else
        {
            result = new String[0];
        }

        return result;
    }

    public FieldSourcePair[] fieldWithOperator(Field search, Operator<Field> operator)
    {
        HashMap<Field, HashSet<String>> dbResults = db.getWithOperator(search, operator);
        return createArrayFromDatabaseSubset(dbResults);
    }

    protected FieldSourcePair[] createArrayFromDatabaseSubset(HashMap<Field, HashSet<String>> subset)
    {
        ArrayList<FieldSourcePair> results = new ArrayList<FieldSourcePair>();

        if (subset == null)
        {
            return new FieldSourcePair[0];
        }
        else
        {
            for (Field key : subset.keySet())
            {
                for (String source : subset.get(key))
                    results.add(new FieldSourcePair(key, source));
            }
            return results.toArray(new FieldSourcePair[results.size()]);
        }
    }
}
