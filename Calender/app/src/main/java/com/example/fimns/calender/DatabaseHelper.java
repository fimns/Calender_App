package com.example.fimns.calender;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "db",null,9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("drop table if exists db");
        } catch(Exception ex) {
        }
        db.execSQL("create table task (i integer PRIMARY KEY, num integer, task1 text, task2 text, task3 text, task4 text, task5 text);");
    //    db.execSQL("insert into task values (3, 5, '2', '3', '4', '5', '6');");
    }
    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
