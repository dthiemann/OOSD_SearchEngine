package edu.uiowa.cs2820.engine.operators;

import edu.uiowa.cs2820.engine.Field;

public class Not implements Operator<Field>
{
    @Override
    public boolean evaluate(Field field1, Field field2)
    {
        return !field1.getValue().equals(field2.getValue());
    }
    
    public String toString()
    {
        return "NOT";
    }

    @Override
    public boolean matchesToken(String token)
    {
        return token.equalsIgnoreCase("NOT") || token.equals("!=");
    }

    @Override
    public String[] allowableTokens()
    {
        return new String[]{ "NOT", "not", "!=" };
    }
    
    
    
}
