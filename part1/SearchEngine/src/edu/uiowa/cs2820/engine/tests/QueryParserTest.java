package edu.uiowa.cs2820.engine.tests;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.query.Query;
import edu.uiowa.cs2820.engine.queryparser.QueryParser;

public class QueryParserTest
{  
    
    @Test
    public void test_simple_single_query_parse()
    {
        QueryParser parser = new QueryParser();
        String queryString = "field:name Tom";
        Query parsedQuery = new Query(new Field("name", "Tom"));

        Query actual = parser.parse(queryString);
        
    }
}
