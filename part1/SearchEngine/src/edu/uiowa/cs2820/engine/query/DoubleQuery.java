package edu.uiowa.cs2820.engine.query;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.Operator;
import edu.uiowa.cs2820.engine.operators.Or;

public class DoubleQuery implements Queryable
{
    private Queryable query1;
    private Queryable query2;
    private Operator<Boolean> operator;
    

    public DoubleQuery(Queryable query1, Queryable query2)
    {
        this.query1 = query1;
        this.query2 = query2;
        this.operator = new Or();
    }

    public DoubleQuery(Queryable query1, Queryable query2, Operator<Boolean> operator)
    {
        this.query1 = query1;
        this.query2 = query2;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfiedBy(Field field)
    {
        return operator.evaluate(query1.isSatisfiedBy(field), query2.isSatisfiedBy(field));
    }
    
    public String toString()
    {
        return "[ (" + query1 + ") " + operator.getClass().getSimpleName() + " (" + query2 + ") ]";
    }
}
