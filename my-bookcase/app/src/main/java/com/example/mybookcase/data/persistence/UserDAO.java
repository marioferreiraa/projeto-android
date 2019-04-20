package com.example.mybookcase.data.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mybookcase.data.model.User;

public class UserDAO {

    private SQLiteDatabase myDatabase;

    public long insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("password", user.getSenha());
        contentValues.put("name", user.getNomeCompleto());
        if (getUser(user.getNomeCompleto())) {
            return myDatabase.insert(Database.TABLE_NAME, null, contentValues);

        } else {
            return -1;
        }
    }

    public boolean getUser(String name) {
        Cursor cursor1 = myDatabase.rawQuery("SELECT * from table_user WHERE name = '" + name + "'", null);
        User userTemp = null;
        while (cursor1.moveToNext()) {
            userTemp = new User(cursor1.getString(1), cursor1.getString(2), cursor1.getString(3));
        }
        cursor1.close();
        boolean canInsert = userTemp == null;
        return canInsert;
    }

}
