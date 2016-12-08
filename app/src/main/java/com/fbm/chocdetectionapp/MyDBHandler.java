package com.fbm.chocdetectionapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tahta on 03/05/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "receiverDB.db";
    private static final String TABLE_RECEIVERS = "receivers";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NUMBER = "number";
    public static final String COLUMN_SMS = "sms";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_RECEIVERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NUMBER
                + " TEXT," + COLUMN_SMS + " TEXT" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }



    //add receiver
    public void addReceiver(Receiver receiver) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, receiver.get_number());
        values.put(COLUMN_SMS, receiver.get_sms());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RECEIVERS, null, values);
        db.close();
    }


    //replace row
    public void replace(Receiver receiver)
    {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, receiver.get_number());
        values.put(COLUMN_SMS, receiver.get_sms());

        SQLiteDatabase db = this.getWritableDatabase();

        onUpgrade(db,1,1);
        //SQLiteDatabase db2 = this.getWritableDatabase();


        db.insert(TABLE_RECEIVERS, null, values);
        db.close();


    }

    //upgrade DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIVERS);
        onCreate(db);

    }



    //chech if db is empty
    public boolean checkForTables(){
        boolean hasTables = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_RECEIVERS, null);

        if(cursor != null && cursor.getCount() > 0){
            hasTables=true;
            cursor.close();
        }

        return hasTables;
    }


    //find receiver
    public Receiver findReceiver() {
        String query = "Select * FROM " + TABLE_RECEIVERS + " WHERE " + COLUMN_ID + " =  \"" + 1 + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Receiver receiver = new Receiver();



        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            receiver.set_id(Integer.parseInt(cursor.getString(0)));
            receiver.set_number(cursor.getString(1));
            receiver.set_sms(cursor.getString(2));



            cursor.close();
        } else {
            receiver=null;
        }
        db.close();
        return receiver;
    }


}
