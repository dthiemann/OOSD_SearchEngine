package edu.uiowa.cs2820.engine;

public class Field
{
    private String fieldName;
    private String value;

    public Field(String fieldName, String value)
    {
        this.fieldName = fieldName;
        this.value = value;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getValue()
    {
        return value;
    }
}
