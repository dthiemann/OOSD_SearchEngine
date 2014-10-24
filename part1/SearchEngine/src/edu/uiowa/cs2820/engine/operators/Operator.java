package edu.uiowa.cs2820.engine.operators;

import edu.uiowa.cs2820.engine.Field;

public interface Operator
{
    public boolean evaluate(Field field1, Field field2);
}