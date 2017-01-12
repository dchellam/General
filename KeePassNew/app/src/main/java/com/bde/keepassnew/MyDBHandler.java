package com.bde.keepassnew;

// This class handles all the database activities

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Credentials2.db";
    public static final String TABLE_CREDENTIALS = "Credentials2";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ENTRYNAME = "EntryName";
    public static final String COLUMN_USERNAME = "UserName";
    public static final String COLUMN_PASSWORD = "Password";

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CREDENTIALS + "(" +
                //COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                //COLUMN_ENTRYNAME + " TEXT NOT NULL, " +
                COLUMN_ENTRYNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_USERNAME + " TEXT NOT NULL, " +
                COLUMN_PASSWORD + " TEXT NOT NULL " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIALS);
        onCreate(db);
    }

    /**
     * Add a new row to the database
     * @param entry
     */
    public long addEntry(Entries entry){
        long retVal = 0;
        ContentValues values = new ContentValues();
        values.put(COLUMN_ENTRYNAME, entry.get_entryName());
        values.put(COLUMN_USERNAME, entry.get_userName());
        values.put(COLUMN_PASSWORD, entry.get_password());
        SQLiteDatabase db = getWritableDatabase();
        retVal = db.insert(TABLE_CREDENTIALS, null, values);
        db.close();
        Log.i("DCM", (String)new String("insert return code = "+retVal+""));
        return retVal;
    }

    //Delete a product from the database
    /*
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }
*/
    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CREDENTIALS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME));
                dbString += "        ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_USERNAME));
                dbString += "        ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_PASSWORD));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<String> dbGetEntriesList(){
        ArrayList<String> entriesList = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();//getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CREDENTIALS + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);

        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)) != null) {
                entriesList.add( recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)));
            }
            recordSet.moveToNext();
        }
        db.close();
        return entriesList;
    }

    public Entries dbGetEntryDetails(String entryName, int pos){
        Entries entry = new Entries();
        SQLiteDatabase db = getReadableDatabase();//getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CREDENTIALS + " WHERE " + COLUMN_ENTRYNAME + " = '" + entryName + "'";// why not leave out the WHERE  clause?

        Log.i("DCM", query);

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);

        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)) != null) {
                Log.i("DCM", recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)));
                Log.i("DCM", recordSet.getString(recordSet.getColumnIndex(COLUMN_USERNAME)));
                Log.i("DCM", recordSet.getString(recordSet.getColumnIndex(COLUMN_PASSWORD)));
                entry.set_entryName(recordSet.getString(recordSet.getColumnIndex(COLUMN_ENTRYNAME)));
                entry.set_userName(recordSet.getString(recordSet.getColumnIndex(COLUMN_USERNAME)));
                entry.set_password(recordSet.getString(recordSet.getColumnIndex(COLUMN_PASSWORD)));
            }
            recordSet.moveToNext();
        }
        db.close();
        return entry;
    }

}