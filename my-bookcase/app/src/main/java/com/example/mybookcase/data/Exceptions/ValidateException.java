package com.example.mybookcase.data.Exceptions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class ValidateException extends Exception{

    private AlertDialog alerta;

    /**
     * Construtor padrão vazio.
     */
    public ValidateException(){}


    /**
     * Construtor recebendo uma excessão, e retornando para a classe pai tratar.
     * @param e
     */
    public ValidateException(Exception e){
        super(e);
    }

    /**
     * Construtor recebendo parametros para montar um toast.
     * @param c
     * @param msg
     */
    public ValidateException(Context c, String msg){
        Toast.makeText(c,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * Construtor recebendo parametros para disparar um dialog.
     * @param c
     * @param a
     * @param title
     * @param msg
     */
    public ValidateException(final Context c, Context a, String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta = builder.create();
        alerta.show();
    }

}
