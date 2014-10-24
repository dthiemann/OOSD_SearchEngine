package edu.uiowa.cs2820.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import edu.uiowa.cs2820.engine.operators.Operator;

public class FieldSearch
{

    private Database db;

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

    public String[] fieldWithOperator(Field search, Operator operator)
    {
        HashSet<String> dbResults = db.getWithOperator(search, operator);
        if (dbResults == null)
            return new String[0];
        else
            return dbResults.toArray(new String[dbResults.size()]);
    }

}
