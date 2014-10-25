package edu.uiowa.cs2820.engine;

import java.util.HashMap;
import java.util.HashSet;

import edu.uiowa.cs2820.engine.query.Queryable;

public class QuerySearch extends FieldSearch
{
    public QuerySearch(Database database)
    {
        super(database);
    }
    
    public FieldSourcePair[] searchQueryable(Queryable query)
    {
        HashMap<Field, HashSet<String>> dbResults = db.searchQueryable(query);
        return createArrayFromDatabaseSubset(dbResults);
    }
}
