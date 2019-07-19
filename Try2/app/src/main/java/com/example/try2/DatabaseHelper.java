package com.example.try2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_Name="favs.db";
    public static final String table_name="favb";
    public static final String col1="ID";
    public static final String col2="Category";
    public static final String col3="Persistent_ID";

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DatabaseHelper(Context context, SQLiteDatabase.OpenParams openParams) {
        super(context, DB_Name, 1, openParams);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Category TEXT, Persistent_ID TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "  +table_name);
        onCreate(db);

    }
    public boolean insertdata(String category, String pID){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cV=new ContentValues();
        cV.put(col2, category);
        cV.put(col3,pID);
        long result= db.insert(table_name,null,cV);
        if(result==-1){
            return false;
        }
        else{return true;}

    }
    public Cursor getData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return res;




}}
