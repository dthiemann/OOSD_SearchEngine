package edu.uiowa.cs2820.engine.operators;

import edu.uiowa.cs2820.engine.Field;

public class GreaterThan implements Operator<Field>
{
    @Override
    public boolean evaluate(Field field1, Field field2)
    {
        return field1.getValue().compareTo(field2.getValue()) > 0;
    }
    
    public String toString()
    {
        return ">";
    }

    @Override
    public boolean matchesToken(String token)
    {
        return token.equals(">");
    }

    @Override
    public String[] allowableTokens()
    {
        return new String[]{ ">" };
    }

    
}
