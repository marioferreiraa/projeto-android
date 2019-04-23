package com.example.mybookcase.data.controller;

import android.app.AlertDialog;
import android.content.Context;

import com.example.mybookcase.data.Exceptions.ValidateException;

public class RegistrationController {

    final String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    final String regexEmail = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";

    public void validaNome(Context c, String nome) throws ValidateException {
        if(nome == null || nome.isEmpty()){
            throw new ValidateException(c,"O nome do usuário não pode ser deixado em branco.");
        }

        if(nome.length() < 7){
            throw new ValidateException(c,"Favor inserir o nome completo.");
        }
    }

    public void validaEmail(Context c, String email) throws ValidateException{

        if(email == null || email.isEmpty()){
            throw new ValidateException(c, "O campo email não pode ser deixado em branco");
        }

        boolean emailValido = email.matches(regexEmail);
        if(!emailValido){
            throw new ValidateException(c,"O email informado é inválido");
        }
    }

    public void validaPassword(Context c, Context a, String password, String confirmPassword) throws ValidateException{

        if(password.isEmpty() || password == null){
            throw new ValidateException(c, "A senha não pode ser deixada em branco");
        }

        boolean strongPassword = password.matches(regexPassword);

        if(!strongPassword){
            throw new ValidateException(c,a,"Senha Fraca","A senha não está forte o suficiente. Para uma senha forte, é necessário que você insira no mínimo oito digitos,"
                +"contendo uma letra maiúscula, uma letra minúscula, um número e um caractere especial");
        }

        if(!confirmPassword.equals(password)){
            throw new ValidateException(c, "As senhas informadas não conferem");
        }
    }

}
