package com.example.mybookcase.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_bookcase";
    public static final String TABLE_NAME = "table_user";

    public Database(Context context){
        super(context, DATABASE_NAME, null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + " ( "
                +" ID_USER INTEGER PRIMARY KEY autoincrement, "
                +" EMAIL VARCHAR(50) NOT NULL, "
                +" NAME VARCHAR(50) NOT NULL, "
                +" PASSWORD VARCHAR(30) NOT NULL) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion == 5 && newVersion == 6){
            Log.d("onUpgrade", "Alterando a tabela");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '"+ TABLE_NAME +"'");
            onCreate(sqLiteDatabase);
        }
    }

}