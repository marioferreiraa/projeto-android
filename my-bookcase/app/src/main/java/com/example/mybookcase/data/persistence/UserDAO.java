package com.example.mybookcase.data.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mybookcase.data.model.User;

public class UserDAO {

    private SQLiteDatabase myDatabase;

    public UserDAO(Context context){
        Database helper = new Database(context);
        myDatabase = helper.getWritableDatabase();
    }

    /**
     * Metodo para inserir usuários na base de dados
     * @param user
     * @return
     */
    public long insertUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", user.getEmail());
        contentValues.put("name", user.getNomeCompleto());
        contentValues.put("password", user.getSenha());
        contentValues.put("cpf", user.getCpf());

        if (getUser(user.getCpf())) {
            return myDatabase.insert(Database.TABLE_NAME, null, contentValues);

        } else {
            return -1;
        }
    }

    /**
     * Método que lista todos os usuários da base de dados e escreve no Log da aplucação
     */
    public void getUsers(){
        Cursor cursor1 = myDatabase.query(Database.TABLE_NAME,null,null,null,null,null,null);
        while (cursor1.moveToNext()){
            Log.i("testeDB", "Email: " + cursor1.getString(1));
            Log.i("testeDB", "Password: " + cursor1.getString(2));
            Log.i("testeDB", "Nome Completo: " + cursor1.getString(3));
            Log.i("testeDB", "CPF: " + cursor1.getString(4));
        }
    }


    /**
     * Metodo para buscar um usuário cadastrado na base
     * @param cpf
     * @return
     */
    public boolean getUser(String cpf) {
        Cursor cursor1;
        User userTemp = null;
        try{
            cursor1 = myDatabase.rawQuery("SELECT * FROM table_user WHERE CPF = '" + cpf + "'", null);

            while (cursor1.moveToNext()) {
                userTemp = new User(cursor1.getString(1), cursor1.getString(2), cursor1.getString(3), cursor1.getString(4));
            }
            cursor1.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        boolean canInsert = userTemp == null;
        return canInsert;
    }

}
