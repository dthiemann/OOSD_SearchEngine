package edu.uiowa.cs2820.engine.query;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.operators.Operator;
import edu.uiowa.cs2820.engine.operators.Or;

public class MultiQuery implements Queryable
{
    private Queryable[] queries;
    private Operator<Boolean> operator;

    public MultiQuery(Queryable... args)
    {
        this(new Or(), args);
    }

    public MultiQuery(Operator<Boolean> operator, Queryable[] args)
    {
        if (args == null || args.length == 0)
            args = new Query[0];
        queries = args;
        this.operator = operator;
    }

    @Override
    public boolean isSatisfiedBy(Field other)
    {
        boolean passedSoFar = true;
        for (Queryable query : queries)
            if (!query.isSatisfiedBy(other))
                passedSoFar = operator.evaluate(passedSoFar, false);
        return passedSoFar;
    }

}
