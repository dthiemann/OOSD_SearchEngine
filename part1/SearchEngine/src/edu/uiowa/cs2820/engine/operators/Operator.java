package edu.uiowa.cs2820.engine.operators;


public interface Operator<T>
{
    public boolean evaluate(T item1, T item2);
}