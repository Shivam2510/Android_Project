package com.example.hbmsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {

    private static final String DBNAME = "expense";
    private static final String TABLENAME = "price";
    private static final int VER = 1;

    // This is a constructor.
    public DBmain(@Nullable Context context) {
        super(context, DBNAME, null,VER);
    }

    // these are 2 method we have to override for SQLiteOpenHelper.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // With help of this we create table by writing query for that
        String query = "CREATE TABLE " + TABLENAME+"(id integer primary key, expense_name text, expense_price text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //if table exist drop it and again create so it update new data.
        String query="drop table if exists " +TABLENAME+ "";
        db.execSQL(query);
    }
}
