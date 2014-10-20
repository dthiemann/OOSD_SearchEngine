package edu.uiowa.cs2820.engine;

import java.util.ArrayList;

import edu.uiowa.cs2820.engine.Field;
import edu.uiowa.cs2820.engine.Database;

public class Indexer {

    private String identifier;
    private String fieldName;
    private ArrayList<String> valueList;

    Indexer(String identifier) throws Exception {
        this.identifier = identifier;
        this.fildName = null;
    }

    public add(Field f) {
        String F = f.getFieldName();

        if (fieldName == null) {
            fieldName = F;
            valueList.add(f.getValue());

        } else if (fieldName == F) {
            valueList.add(f.getValue());

        } else {
            throw new Exception("Field name does not match");
        }

    }

    public close() {
        // I may check database size here.
        Database D = new Database();
        D.store(identifier, fieldName, valueList);
    }

}
