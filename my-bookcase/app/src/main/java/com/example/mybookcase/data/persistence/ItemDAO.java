package com.example.mybookcase.data.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.mybookcase.data.model.Item;

public class ItemDAO {
    private SQLiteDatabase myDatabase;

    public ItemDAO(Context context){
        Database helper = new Database(context);
        myDatabase = helper.getWritableDatabase();
    }
    /**
     * Metodo para inserir Items na base de dados
     * @param
     * @return
     */
    public long insertItem(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", item.getName());
        contentValues.put("description", item.getDescription());
        contentValues.put("type", item.getType());
        //contentValues.put("image", book.getImage());

        if (getItem(item.getName(), item.getDescription())) {
            return myDatabase.insert("table_item", null, contentValues);
        } else {
            return -1;
        }
    }

    /**
     * Método que lista todos os items da base de dados e escreve no Log da aplicação
     */
    public void getItens(){
        Cursor cursor1 = myDatabase.query("table_item",null,null,null,null,null,null);
        while (cursor1.moveToNext()){
            Log.i("testeDB", "name: " + cursor1.getString(1));
            Log.i("testeDB", "description: " + cursor1.getString(2));
            Log.i("testeDB", "type: " + cursor1.getString(3));
            // Log.i("testeDB", "Image " + cursor1.getString(3));
        }
    }
    /**
     * Metodo para buscar um item cadastrado na base
     * @param name,description
     * @return
     */
    public boolean getItem(String name, String description) {
        Cursor cursor1;
        Item itemTemp = null;
        try{
            cursor1 = myDatabase.rawQuery("SELECT * FROM table_item WHERE NAME = '" + name, null);

            while (cursor1.moveToNext()) {
                itemTemp = new Item(cursor1.getString(1), cursor1.getString(2), cursor1.getString(3));
            }
            cursor1.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        boolean canInsert = itemTemp == null;
        return canInsert;
    }
}
