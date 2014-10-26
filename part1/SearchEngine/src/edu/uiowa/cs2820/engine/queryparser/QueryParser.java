package edu.uiowa.cs2820.engine.queryparser;

import java.util.HashMap;
import java.util.StringTokenizer;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.And;
import edu.uiowa.cs2820.engine.operators.Equals;
import edu.uiowa.cs2820.engine.operators.GreaterThan;
import edu.uiowa.cs2820.engine.operators.GreaterThanEqualTo;
import edu.uiowa.cs2820.engine.operators.LessThan;
import edu.uiowa.cs2820.engine.operators.LessThanEqualTo;
import edu.uiowa.cs2820.engine.operators.Not;
import edu.uiowa.cs2820.engine.operators.Operator;
import edu.uiowa.cs2820.engine.operators.Or;
import edu.uiowa.cs2820.engine.query.Query;

public class QueryParser
{
    protected HashMap<String, Operator> operatorMap = new HashMap<String, Operator>();
    protected Operator[] operatorList = { new And(), new Or(), new Not(), new Equals(), new LessThan(), new LessThanEqualTo(), new GreaterThan(),
            new GreaterThanEqualTo() };

    private static final String WHITESPACE = " \t\r\n";
    private static final String QUOTES = "\'\"";
    protected String delimeter = WHITESPACE + QUOTES;

    public QueryParser()
    {
        for (Operator operator : operatorList)
            for (String token : operator.allowableTokens())
                operatorMap.put(token, operator);
    }

    public Query parse(String queryString)
    {
        boolean returnTokens = true;
        StringTokenizer parser = new StringTokenizer(queryString, delimeter, returnTokens);
        String token = null;
        Field currentField = null;
        
        String fieldName = parser.nextToken();
        fieldName = fieldName.split(":")[1];
        
        
        
        

        return null;
    }
}
