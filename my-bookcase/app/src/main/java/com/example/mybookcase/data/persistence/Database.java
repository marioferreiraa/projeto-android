package com.example.mybookcase.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_bookcase";
    public static final String TABLE_NAME = "table_user";

    public Database(Context context){
        super(context, DATABASE_NAME, null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + " ( "
                +" ID_USER INTEGER PRIMARY KEY autoincrement, "
                +" EMAIL VARCHAR(50) NOT NULL, "
                +" NAME VARCHAR(50) NOT NULL, "
                +" PASSWORD VARCHAR(30) NOT NULL) "
        );
        sqLiteDatabase.execSQL(" CREATE TABLE table_book ( "
                +" ID_BOOK INTEGER PRIMARY KEY autoincrement, "
                +" NAME VARCHAR(50) NOT NULL, "
                +" DESCRIPTION VARCHAR(150) ) "
        );
        // TYPE : BOOK OR MOVIE
        sqLiteDatabase.execSQL(" CREATE TABLE table_item ( "
                +" ID_ITEM INTEGER PRIMARY KEY autoincrement, "
                +" NAME VARCHAR(50) NOT NULL, "
                +" DESCRIPTION VARCHAR(1000) NOT NULL, "
                +" TYPE VARCHAR(5) NOT NULL,"
                +" IS_ACERVO VARCHAR(1) NOT NULL,"
                +" IMAGE BLOB )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion == 12 && newVersion == 13){
            Log.d("onUpgrade", "Alterando a tabela");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+ TABLE_NAME +"'");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_book");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_item");
            onCreate(sqLiteDatabase);
        }
    }

}