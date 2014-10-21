package edu.uiowa.cs2820.engine;


public class Indexer
{

    private String identifier;
    private Database database;
    private boolean closed;

    public Indexer(Database database, String identifier)
    {
        this.identifier = identifier;
        this.database = database;
    }

    public void add(Field f)
    {
        if (!closed)
            database.store(f, identifier);
    }

    public void close()
    {
        closed = true;
    }

}
