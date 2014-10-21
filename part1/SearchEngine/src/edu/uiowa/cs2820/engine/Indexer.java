package edu.uiowa.cs2820.engine;

import java.util.ArrayList;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

public class Indexer {

    private String identifier;
    private String fieldName;
    private ArrayList<String> valueList;

    public Indexer(String identifier) {
        this.identifier = identifier;
        this.fieldName = null;
    }

    public void add(Field f) {
        // I feel like this should be something like
        //      database.add(field, identifier)
        // also, the field name does not need to be the same
        // throughout the indexer - Tom
        
        String F = f.getFieldName();

        if (fieldName == null) {
            // it's the 1st field coming in, initialize fieldName
            fieldName = F;
            valueList.add(f.getValue());

        } else if (fieldName == F) {
            // 2nd and future coming fields should match the existing fieldName
            valueList.add(f.getValue());

        } else {
            // fieldName does not match, throw exception
            throw new Exception("Field name does not match");
        }

    }

    public void close() {
        // I may check database size here.
        Database D = new Database();
        D.store(identifier, fieldName, valueList);
    }

}
