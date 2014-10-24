package edu.uiowa.cs2820.engine.operators;

import edu.uiowa.cs2820.engine.Field;


public class Equals implements Operator
{
    public boolean evaluate(Field field1, Field field2)
    {
        return field1.equals(field2);
    }
}