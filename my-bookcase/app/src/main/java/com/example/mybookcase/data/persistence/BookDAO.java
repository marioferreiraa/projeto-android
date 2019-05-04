package com.example.mybookcase.data.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.mybookcase.data.model.Book;
public class BookDAO {
    private SQLiteDatabase myDatabase;

    public BookDAO(Context context){
        Database helper = new Database(context);
        myDatabase = helper.getWritableDatabase();
    }
    /**
     * Metodo para inserir livros na base de dados
     * @param book
     * @return
     */
    public long insertBook(Book book) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", book.getName());
        contentValues.put("description", book.getDescription());
        //contentValues.put("image", book.getImage());

        if (getBook(book.getName(), book.getDescription())) {
            return myDatabase.insert("table_book", null, contentValues);
        } else {
            return -1;
        }
    }

    /**
     * Método que lista todos os livros da base de dados e escreve no Log da aplicação
     */
    public void getBooks(){
        Cursor cursor1 = myDatabase.query("table_book",null,null,null,null,null,null);
        while (cursor1.moveToNext()){
            Log.i("testeDB", "name: " + cursor1.getString(1));
            Log.i("testeDB", "description: " + cursor1.getString(2));
           // Log.i("testeDB", "Image " + cursor1.getString(3));
        }
    }
    /**
     * Metodo para buscar um usuário cadastrado na base
     * @param name,description
     * @return
     */
    public boolean getBook(String name, String description) {
        Cursor cursor1;
        Book bookTemp = null;
        try{
            cursor1 = myDatabase.rawQuery("SELECT * FROM table_book WHERE NAME = '" + name, null);

            while (cursor1.moveToNext()) {
                bookTemp = new Book(cursor1.getString(1), cursor1.getString(2));
            }
            cursor1.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        boolean canInsert = bookTemp == null;
        return canInsert;
    }

}
