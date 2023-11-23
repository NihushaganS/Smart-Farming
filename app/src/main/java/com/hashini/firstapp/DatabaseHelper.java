package com.hashini.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    public static final String DATABASE_NAME = "Users_details.db";
    public static final String TABLE_NAME = "users_details_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "User_Name";
    public static final String COL_3 = "user_loaction";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = " CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT)";
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String User_Name, String user_loaction) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, User_Name);
        contentValues.put(COL_3, user_loaction);
        long insertionResult = database.insert(TABLE_NAME, null, contentValues);
        return (insertionResult != -1);
    }
    public Cursor getAllData() {
        SQLiteDatabase database = this.getWritableDatabase();
        String getAllDataQuery = " SELECT * FROM " + TABLE_NAME ;
        Cursor result = database.rawQuery(getAllDataQuery, null );
        return result;
    }

    public boolean updateData(String id, String User_Name, String user_loaction) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, User_Name);
        contentValues.put(COL_3, user_loaction);
        int numberOfAffectedRows = database.update(TABLE_NAME, contentValues,"id = ?", new String[] {id});
        return (numberOfAffectedRows > 0);
    }
    public int deleteData(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLE_NAME, "id = ?", new String[] {id});

    }
}
