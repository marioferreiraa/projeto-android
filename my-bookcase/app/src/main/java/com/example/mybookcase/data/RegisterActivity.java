package com.example.mybookcase.data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.User;
import com.example.mybookcase.data.persistence.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fieldName = findViewById(R.id.editName);
        final EditText fieldEmail = findViewById(R.id.editEmail);
        final EditText fieldPass = findViewById(R.id.editPassword);
        final EditText fieldConfirm = findViewById(R.id.editConfirm);
        final Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(fieldEmail.getText().toString(),fieldName.getText().toString(),fieldPass.getText().toString());
                UserDAO userDAO = new UserDAO(getApplicationContext());
                long insertUser = userDAO.insertUser(user);

                if(insertUser != -1){
                    Toast.makeText(getApplicationContext(),"Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"O usuário já encontra-se na base de dados", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
