package com.example.mybookcase.data.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.mybookcase.data.model.Item;

import java.util.ArrayList;
import java.util.List;

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
        contentValues.put("path_img", item.getPathImage());
        contentValues.put("is_acervo", item.getIsAcervo());

        if (getItem(item.getName(),item.getDescription())) {
            System.out.println("============================================ Inseriu o item ===========================================");
            return myDatabase.insert("table_item", null, contentValues);
        } else {
            return -1;
        }
    }

    /**
     * Método que lista todos os items da base de dados e escreve no Log da aplicação
     */
    public ArrayList<Item> getItens(){
        ArrayList<Item> listaItens = new ArrayList<>();
        Cursor cursor1 = myDatabase.query("table_item",null,null,null,null,null,null);
        System.out.println("============================================ Buscando os item ===========================================");
        while (cursor1.moveToNext()){
            Log.i("testeDB", "id: " + cursor1.getString(0));
            Log.i("testeDB", "name: " + cursor1.getString(1));
            Log.i("testeDB", "description: " + cursor1.getString(2));
            Log.i("testeDB", "type: " + cursor1.getString(3));
            Log.i("testeDB","path_img: " + cursor1.getString(4));
            Log.i("testeDB","isAcervo: " + cursor1.getString(5));

            Item item = new Item(cursor1.getInt(0),cursor1.getString(1), cursor1.getString(2),cursor1.getString(3),cursor1.getString(4),cursor1.getString(5));
            listaItens.add(item);
        }

        return listaItens;
    }

    /**
     * Metodo para buscar um item cadastrado na base
     * @param id
     * @return
     */
    public boolean getItem(int id) {
        Cursor cursor1;
        Item itemTemp = null;
        try{
            cursor1 = myDatabase.rawQuery("SELECT * FROM table_item WHERE ID_ITEM = '" + id, null);
            while (cursor1.moveToNext()) {
                try{
                    itemTemp = new Item(cursor1.getInt(1), cursor1.getString(2), cursor1.getString(3), cursor1.getString(4), cursor1.getString(5), cursor1.getString(6));
                    System.out.println("============================================ Buscando o item ===========================================");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            cursor1.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        boolean canInsert = itemTemp == null;
        return canInsert;
    }

    public boolean getItem(String name, String description) {
        Cursor cursor1;
        Item itemTemp = null;
        try{
            cursor1 = myDatabase.rawQuery("SELECT * FROM table_item WHERE NAME = '" + name, null);

            while (cursor1.moveToNext()) {
                try{
                    //itemTemp = new Item(cursor1.getString(1), cursor1.getString(2), cursor1.getString(3));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            cursor1.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        boolean canInsert = itemTemp == null;
        return canInsert;
    }
}
