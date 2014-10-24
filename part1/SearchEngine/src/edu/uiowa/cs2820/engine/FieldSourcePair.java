package edu.uiowa.cs2820.engine;

public class FieldSourcePair
{
    private Field field;
    private String source;

    public FieldSourcePair(Field field, String source)
    {
        super();
        this.field = field;
        this.source = source;
    }

    public Field getField()
    {
        return field;
    }

    public String getSource()
    {
        return source;
    }
    
    public String toString()
    {
        return field + " found in " + source;
    }
}
