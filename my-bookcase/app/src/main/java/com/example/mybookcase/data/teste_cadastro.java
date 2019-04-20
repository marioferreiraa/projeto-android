package com.example.mybookcase.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.User;
import com.example.mybookcase.data.persistence.MySharedPreference;
import com.example.mybookcase.data.persistence.UserDAO;

public class teste_cadastro extends AppCompatActivity {

    //private MySharedPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_cadastro);

        // Shared Preferences
        //preference = MySharedPreference.getInstance(this);

        /*
        Criando usuários para inserir no banco

        User user1 = new User("aaaa@aa.aa","aaaaa aaaaaa","123Aa@aa","12345678909");
        User user2 = new User("bbbb@bb.bb","bbbbb bbbbbb","123Bb@bb","11111111111");

        UserDAO userDAO = new UserDAO(getApplicationContext());

        userDAO.insertUser(user1);
        userDAO.insertUser(user2);

        userDAO.getUsers();*/

    }
}
