package com.example.shivam.databasehelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shivam on 11/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public Context mContext;
    public String db_name;
    public int db_version;
    SQLiteDatabase db;
    public DatabaseHelper(Context context,String db_name,int db_version)
    {
        super(context,db_name,null,db_version);
        this.mContext=context;
        this.db_name=db_name;
        this.db_version=db_version;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void executeQuery(String query)
    {
        db.execSQL(query);
    }
    public Cursor executeSelect(String query)
    {
        Cursor result = db.rawQuery(query,null);
        return result;
    }

    @Override
    public synchronized void close() {
        db.close();
    }
}
