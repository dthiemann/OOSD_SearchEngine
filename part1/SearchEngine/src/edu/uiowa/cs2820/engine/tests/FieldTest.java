package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

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
        assertEquals(field, testField.getFieldName());
        assertEquals(value, testField.getValue());
    }    
    
    @Test
    public void test_equals()
    {
        String field = "" + Math.random();
        String value = "" + Math.random();
        Field field1 = new Field(field, value);
        Field field2 = new Field(field, value);
        
        assertEquals(field1, field2);
        assertTrue(field1.equals(field2));
        assertFalse(field1.equals(null));
        assertFalse(field1.equals("some string"));
        assertFalse(field1.equals(new Object()));
    }
    
    @Test
    public void test_hash_code()
    {
        String field = "" + Math.random();
        String value = "" + Math.random();
        Field field1 = new Field(field, value);
        Field field2 = new Field(field, value);
        
        assertEquals(field1.hashCode(), field2.hashCode());
        assertEquals((field + value).hashCode(), field1.hashCode());
        Field differentField = new Field(value, field);
        assertNotSame(field1.hashCode(), differentField.hashCode());
    }
    
    @Test
    public void test_to_string()
    {
        String field = "" + Math.random();
        String value = "" + Math.random();
        Field field1 = new Field(field, value);
        assertEquals(( "(" + field + " : " + value + ")"), field1.toString());
    }
}
