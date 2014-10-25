package edu.uiowa.cs2820.engine.query;

import edu.uiowa.cs2820.engine.Field;

public interface Queryable
{
    public boolean isSatisfiedBy(Field other);
}
