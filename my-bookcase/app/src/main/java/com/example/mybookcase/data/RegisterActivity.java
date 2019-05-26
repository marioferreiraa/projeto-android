package com.example.mybookcase.data;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.Exceptions.ValidateException;
import com.example.mybookcase.data.controller.RegistrationController;
import com.example.mybookcase.data.facade.RegistrationFacade;
import com.example.mybookcase.data.model.User;
import com.example.mybookcase.data.persistence.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText fieldName = findViewById(R.id.editName);
        final EditText fieldEmail = findViewById(R.id.editEmail);
        final EditText fieldPass = findViewById(R.id.editPassword);
        final EditText fieldConfirm = findViewById(R.id.editConfirm);
        final Button buttonRegister = findViewById(R.id.buttonRegister);
        final TextView linkLogin = findViewById(R.id.lnkLogin);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = fieldEmail.getText().toString();
                String name = fieldName.getText().toString();
                String password = fieldPass.getText().toString();
                String confirm = fieldConfirm.getText().toString();

                User user = new User(email, name, password);
                RegistrationFacade rF = new RegistrationFacade(getApplicationContext(), RegisterActivity.this);
                try {
                    rF.insertUser(user,confirm);
                } catch (ValidateException e) {
                    e.printStackTrace();
                }

                /*if(insertUser != -1){
                    Toast.makeText(getApplicationContext(),"Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"O usuário já encontra-se na base de dados", Toast.LENGTH_LONG).show();
                }*/
            }
        });

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
