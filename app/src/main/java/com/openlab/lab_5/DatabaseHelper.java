package com.openlab.lab_5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String dbname ="todo.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table todo_table(id integer primary key, title text, description text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(int id, String title, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("title", title);
        cv.put("description", des);
        long done = db.insert("todo_table", null, cv);
        if(done == -1) return false;
        else return true;
    }
    public boolean updateData(String id, String title, String des){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("description", des);
        long done = db.update("todo_table",  cv, "id=?", new String[]{id});
        if(done == -1) return false;
        else return true;
    }
    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long done = db.delete("todo_table",  "id=?", new String[]{id});
        if(done == -1) return false;
        else return true;
    }
    public Cursor readAllTaskData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("select * from todo_table", null);
        if(data.getCount() > 0) return data;
        else return null;
    }
}
