package edu.uiowa.cs2820.engine.operators;

import edu.uiowa.cs2820.engine.Field;

public class GreaterThanEqualTo extends GreaterThan
{
    @Override
    public boolean evaluate(Field field1, Field field2)
    {
        return super.evaluate(field1, field2) || field1.equals(field2);
    }
    
    public String toString()
    {
        return ">=";
    }
}
