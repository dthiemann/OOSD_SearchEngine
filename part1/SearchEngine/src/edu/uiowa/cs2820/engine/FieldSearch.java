package edu.uiowa.cs2820.engine;

import java.util.Set;

public class FieldSearch
{
    private Database database;

    public FieldSearch(Database database)
    {
        this.database = database;
    }

    public String[] findEquals(Field search)
    {
        Set<String> results = database.getValue(search);
        if (results == null)
            return new String[]{};
        return results.toArray(new String[results.size()]);
    }

}
