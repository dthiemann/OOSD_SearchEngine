package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.uiowa.cs2820.engine.Field;

public class FieldTest
{
    @Test
    public void assert_that_field_constructor_and_getters_work()
    {
        String field = "" + Math.random();
        String value = "" + Math.random();
        Field testField = new Field(field, value);
        assertEquals(testField.getFieldName(), field);
        assertEquals(testField.getValue(), value);
        
    }    
}
