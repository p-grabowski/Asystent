package com.example.z370.asystent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BazaPunktow extends SQLiteOpenHelper {

    public static final String DB_name = "Punkty.db";
    public static final String Table_name = "punkty_table";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "Nazwa";
    public static final String COL_3 = "X";
    public static final String COL_4 = "Y";
    public static final String COL_5 = "H";
    public static final String COL_6 = "_idZ";
    public static final String Table_name2 = "zbiory_table";
    public static final String COL_7 = "Nazwa_zbioru";

    public BazaPunktow(Context context) {
        super(context, DB_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_name + "( "+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_2 +" TEXT, "+
                COL_3 +" DOUBLE(10,3), "+
                COL_4 +" DOUBLE(10,3), "+
                COL_5 +" DOUBLE(4,3), "+
                COL_6 +" INTEGER, FOREIGN KEY("+
                COL_6 +") REFERENCES Nazwa_zbioru(idZBIORU) );");

        db.execSQL("CREATE TABLE " + Table_name2 + "( "+
                "_idZ INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL_7 +" TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name2);
        onCreate(db);
    }

    public boolean dodajpunkt(String nazwa, double x, double y, double h, int idz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nazwa);
        contentValues.put(COL_3, x);
        contentValues.put(COL_4, y);
        contentValues.put(COL_5, h);
        contentValues.put(COL_6, idz);
        long result = db.insert(Table_name, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor pokazcalabaze(Integer idz){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name+" WHERE "+ COL_6 +" = '"+ idz +"';", null);
        return res;
    }

    public Cursor pokazXYH(String nazwa, Integer idz){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name+" where "+ COL_2 +" = '" + nazwa + "' AND "+COL_6 +" = '"+ idz +"';", null);
        return res;
    }

    public boolean zmienpunkt(String id, String nazwa, double x, double y, double h){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nazwa);
        contentValues.put(COL_3, x);
        contentValues.put(COL_4, y);
        contentValues.put(COL_5, h);
        db.update(Table_name, contentValues, "_id = ?", new String[]{ id });
        return true;
    }

    public Integer usunpunkt(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "_id = ?", new String[] { id });
    }

    public Integer usunpunktnazwa(String  Nazwa){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name, "Nazwa = ?", new String[] { Nazwa });
    }

    public boolean dodajZbior(String nazwa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_7, nazwa);
        long result = db.insert(Table_name2, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Integer usunZbiorNazwa(String  Nazwa){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name2, "Nazwa_zbioru = ?", new String[] { Nazwa });
    }

    public Cursor pokazZbiory(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name2, null);
        return res;
    }

    public boolean sprawdzPunkt(String nazwa, int idz){  //sprawdz czy istnieje, jesli istenieje więcej niż 0 rekordów to true, mniej - false
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name+" where "+COL_2+" ='" + nazwa + "' AND "+COL_6 +" = '"+ idz +"';", null);
        if (res.getCount() > 0) return true;
        else return false;
    }

    public boolean sprawdzZbior(String nazwa){  //sprawdz czy istnieje, jesli istenieje więcej niż 0 rekordów to true, mniej - false
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name2+" where "+COL_7+" ='" + nazwa + "';", null);
        if (res.getCount() > 0) return true;
        else return false;
    }

        public Integer idNazwaZbior(String nazwa){ //pobiera nazwę, a zwraca id zbioru
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select "+COL_6+" from "+Table_name2+" where "+COL_7+" = '" + nazwa + "';", null);
        res.moveToFirst();
        return res.getInt(0);
    }
}
