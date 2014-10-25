package edu.uiowa.cs2820.engine.operators;

public class Or implements Operator<Boolean>
{

    @Override
    public boolean evaluate(Boolean item1, Boolean item2)
    {
        return item1 || item2;
    }
    
    public String toString()
    {
        return "OR";
    }

}
