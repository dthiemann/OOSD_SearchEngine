package edu.uiowa.cs2820.engine.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.Equals;
import edu.uiowa.cs2820.engine.operators.GreaterThan;
import edu.uiowa.cs2820.engine.operators.GreaterThanEqualTo;
import edu.uiowa.cs2820.engine.operators.LessThan;
import edu.uiowa.cs2820.engine.operators.LessThanEqualTo;
import edu.uiowa.cs2820.engine.operators.Not;
import edu.uiowa.cs2820.engine.query.Query;

public class QueryTest
{
    @Test
    public void test_default_operator()
    {
        Field field1 = new Field("fieldname", "value1");
        Field field2 = new Field("fieldname", "value1");
        Field field3 = new Field("fieldname", "value2");
        Field field4 = new Field("fieldname2", "value1");
        Query query = new Query(field1);
        
        assertTrue(query.getOperator() instanceof Equals);
        assertTrue(query.isSatisfiedBy(field1));
        assertTrue(query.isSatisfiedBy(field2));
        assertFalse(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_equals_operator()
    {
        Field field1 = new Field("fieldname", "value1");
        Field field2 = new Field("fieldname", "value1");
        Field field3 = new Field("fieldname", "value2");
        Field field4 = new Field("fieldname2", "value1");
        Query query = new Query(field1, new Equals());
        
        assertTrue(query.getOperator() instanceof Equals);
        assertTrue(query.isSatisfiedBy(field1));
        assertTrue(query.isSatisfiedBy(field2));
        assertFalse(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_less_than_operator()
    {
        Field field1 = new Field("fieldname", "a");
        Field field2 = new Field("fieldname", "b");
        Field field3 = new Field("fieldname", "c");
        Field field4 = new Field("fieldname2", "b");
        Query query = new Query(field2, new LessThan());
        
        assertTrue(query.getOperator() instanceof LessThan);
        assertTrue(query.isSatisfiedBy(field1));
        assertFalse(query.isSatisfiedBy(field2));
        assertFalse(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_less_than_equals_operator()
    {
        Field field1 = new Field("fieldname", "a");
        Field field2 = new Field("fieldname", "b");
        Field field3 = new Field("fieldname", "c");
        Field field4 = new Field("fieldname2", "b");
        Query query = new Query(field2, new LessThanEqualTo());
        
        assertTrue(query.getOperator() instanceof LessThanEqualTo);
        assertTrue(query.isSatisfiedBy(field1));
        assertTrue(query.isSatisfiedBy(field2));
        assertFalse(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_greater_than_operator()
    {
        Field field1 = new Field("fieldname", "a");
        Field field2 = new Field("fieldname", "b");
        Field field3 = new Field("fieldname", "c");
        Field field4 = new Field("fieldname2", "b");
        Query query = new Query(field2, new GreaterThan());
        
        assertTrue(query.getOperator() instanceof GreaterThan);
        assertFalse(query.isSatisfiedBy(field1));
        assertFalse(query.isSatisfiedBy(field2));
        assertTrue(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_greater_than_equals_operator()
    {
        Field field1 = new Field("fieldname", "a");
        Field field2 = new Field("fieldname", "b");
        Field field3 = new Field("fieldname", "c");
        Field field4 = new Field("fieldname2", "b");
        Query query = new Query(field2, new GreaterThanEqualTo());
        
        assertTrue(query.getOperator() instanceof GreaterThanEqualTo);
        assertFalse(query.isSatisfiedBy(field1));
        assertTrue(query.isSatisfiedBy(field2));
        assertTrue(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    @Test
    public void test_not_operator()
    {
        Field field1 = new Field("fieldname", "a");
        Field field2 = new Field("fieldname", "b");
        Field field3 = new Field("fieldname", "c");
        Field field4 = new Field("fieldname2", "b");
        Query query = new Query(field2, new Not());
        
        assertTrue(query.getOperator() instanceof Not);
        assertTrue(query.isSatisfiedBy(field1));
        assertFalse(query.isSatisfiedBy(field2));
        assertTrue(query.isSatisfiedBy(field3));
        assertFalse(query.isSatisfiedBy(field4));
    }
    
    
    
}
