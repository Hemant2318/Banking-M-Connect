package com.example.banking_m_connect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9923576893,'Ramesh Patel',500.00,'ramesh02@gmail.com','XXXXXXX4531','MAHB9876043')");
        db.execSQL("insert into user_table values(6309842674,'Suresh Sharma',2000.67,'suresh03@gmail.com','XXXXXXX4367','PUNB9806543')");
        db.execSQL("insert into user_table values(9147394920,'Monika Patel',100000.56,'monika04@gmail.com','XXXXXXX8931','AUNB7654021')");
        db.execSQL("insert into user_table values(9634957374,'Shrikant Tiwari',3000.01,'shrikant05@gmail.com','XXXXXXX0720','GUJB6543210')");
        db.execSQL("insert into user_table values(9458384893,'JK Talpade',2500.48,'talpade06@gmail.com','XXXXXXX7061','KANB5432109')");
        db.execSQL("insert into user_table values(9237589473,'Hardik Shah',8000.16,'shah07@gmail.com','XXXXXXX5072','TMLB4321098')");
        db.execSQL("insert into user_table values(9737590348,'Ritesh Pandya',7500.00,'pandya08@gmail.com','XXXXXXX2078','RAJB3210987')");
        db.execSQL("insert into user_table values(8389538958,'Nirav Modi',2100.22,'niravmodi09@gmail.com','XXXXXXX2836','PBNB2109876')");
        db.execSQL("insert into user_table values(8429845743,'Jay Shah',3700.46,'jayshah10@gmail.com','XXXXXXX3079','JKBO1098705')");
        db.execSQL("insert into user_table values(8835589474,'Gautam Mishra',5500.90,'mishra01@gmail.com','XXXXXXX1964','BIHB0980654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
