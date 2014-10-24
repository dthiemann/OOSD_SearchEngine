package edu.uiowa.cs2820.engine;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

/*
 * Indexer, currently directly exposed to the user for indexing documents.
 *
 * author: Wenbin Lu
 * date: 10/24/14
 *
 */

public class Indexer {

    private String identifier;
    private Database db;
    private boolean closed;

    public Indexer(Database db, String identifier) {
        this.identifier = identifier;
        this.db = db;
    }

    public void add(Field f) {
        if (!closed)
            db.store(f, identifier);

    }

    public void close() {
        closed = true;
    }

}
