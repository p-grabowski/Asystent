package com.example.z370.asystent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BazaPunktow extends SQLiteOpenHelper {

    public static final String DB_name = "Punkty.db";
    public static final String Table_name = "punkty_table";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Nazwa";
    public static final String COL_3 = "X";
    public static final String COL_4 = "Y";
    public static final String COL_5 = "H";


    public BazaPunktow(Context context) {
        super(context, DB_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE " + Table_name + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT, X DOUBLE(8,3), Y DOUBLE(8,3), H DOUBLE(4,3));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + Table_name);
onCreate(db);
    }

    public boolean dodaj( String nazwa, double x, double y, double h){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nazwa);
        contentValues.put(COL_3, x);
        contentValues.put(COL_4, y);
        contentValues.put(COL_5, h);
        long result = db.insert(Table_name, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
}
