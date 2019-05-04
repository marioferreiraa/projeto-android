package com.example.mybookcase.data.facade;

import android.content.Context;
import android.widget.Toast;

import com.example.mybookcase.data.Exceptions.ValidateException;
import com.example.mybookcase.data.controller.RegistrationController;
import com.example.mybookcase.data.model.Book;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.model.User;
import com.example.mybookcase.data.persistence.BookDAO;
import com.example.mybookcase.data.persistence.ItemDAO;
import com.example.mybookcase.data.persistence.UserDAO;

public class RegistrationFacade {

    public RegistrationFacade(){
        super();
    }

    public RegistrationFacade(Context c, Context a){
        this.c = c;
        this.activity = a;
    }

    Context c;
    Context activity;

    RegistrationController rC = new RegistrationController();

    public void validateUser(User user, String confirmPass) throws ValidateException {
        rC.validaNome(c, user.getNomeCompleto());
        rC.validaEmail(c, user.getEmail());
        rC.validaPassword(c, activity, user.getSenha(), confirmPass);
    }

    public void insertUser(User user, String confirmPass) throws ValidateException {
        this.validateUser(user, confirmPass);
        UserDAO userDAO = new UserDAO(c);
        long insertUser = userDAO.insertUser(user);
        if(insertUser != -1){
            Toast.makeText(c,"Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c,"O usuário já encontra-se na base de dados", Toast.LENGTH_LONG).show();
        }
    }
    public void insertBook(Book book) throws ValidateException {
        BookDAO bookDAO = new BookDAO(c);
        long insertBook = bookDAO.insertBook(book);
        if(insertBook != -1){
            Toast.makeText(c,"Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c,"O livro já encontra-se na base de dados", Toast.LENGTH_LONG).show();
        }
    }

    public void insertItem(Item item) throws ValidateException {
        ItemDAO itemDAO = new ItemDAO(c);
        long insertItem = itemDAO.insertItem(item);
        if(insertItem != -1){
            Toast.makeText(c,"Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c,"O Item já encontra-se na base de dados", Toast.LENGTH_LONG).show();
        }
    }

}
