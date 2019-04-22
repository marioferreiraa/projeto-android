package com.example.mybookcase.data.Exceptions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class ValidateException extends Exception{

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
     * @param alerta
     * @param msg
     */
    public ValidateException(Context c, AlertDialog alerta, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(msg);
        builder.setMessage(msg);
        builder.setPositiveButton("positivo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MainActivity.this, "POSITIVO=" + which, Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        alerta.show();
    }

}
