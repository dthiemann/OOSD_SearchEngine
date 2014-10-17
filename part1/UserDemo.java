package org.uiowa.cs2820.demo;

import org.uiowa.cs2820.engine.Field;
import org.uiowa.cs2820.engine.Indexer;
import org.uiowa.cs2820.engine.FieldSearch;

import java.io.*;

class UserFileIndex {

   String fileName;
   FileReader F;
   BufferedReader B; 

   UserFileIndex(String fileName) throws Exception {
       this.fileName = fileName;
       this.F = new FileReader(fileName);
       this.B = new BufferedReader(F); 
       }

   public void oneLineIndex(String identifier, String line) throws Exception {
       Indexer I = new Indexer(identifier);
       String[] Words = line.split("\\s+");
       // Words[0] is field name for indexing
       // and remaining Words[i] are to be indexed
       if (Words[0].length() == 0) return;  // must have field
       String fieldName = Words[0];
       for (int i=1; i<Words.length; i++) { 
          Field f = new Field(fieldName,Words[i]);
          I.add(f); // (fieldName,Value) added to index
          }
       I.close();
       }

   public void indexLines() throws Exception {
       String line = null, identifier;
       int counter = 0;
       line = B.readLine();
       while (true) {
          identifier = FileName + "-" + counter; 
          if (line == null) break;
          oneLineIndex(identifier,line);
          line = B.readLine();
          counter += 1;
          }
       }
   }

class UserLookup {

   ArrayList<Field> lookups;

   UserLookup() {
       this.lookups = new ArrayList<Field>;
       String[] sampfields = "amount 900 sku 12346 text blue".split("\\s+");
       for (i=0; i<sampfields.length; i += 2) {
           Field f = new Field(sampfields[i],sampfields[i+1]);
           lookups.add(f);
           }
       }
   
   public void testLookups() throws Exception {
       FieldSearch T = new FieldSearch();
       for (Field f: lookups) {
           System.out.print("Lookup field " + f.getFieldName());
           System.out.print(" for value " + f.getFieldValue());
           System.out.println(" results follow:");
           String[] result = T.findEquals(lookups.get(i));  
           for (String e: result) {
              System.out.println("\t\t"+e); 
              }
           }
       }

   }

public class UserDemo {
   // main expects to get a file names as strings for parameters
   public static void main(String[] args) throws Exception {
      // index a bunch of files
      for (String e: args) {
         UserFileIndex T = new UserFileIndex(e);
         T.indexLines();
         }
      UserLookup L = new UserLookup();
      L.testLookups();
      }
   }
