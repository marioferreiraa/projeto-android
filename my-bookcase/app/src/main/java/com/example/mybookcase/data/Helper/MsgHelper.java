package com.example.mybookcase.data.Helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MsgHelper {

    private AlertDialog alerta;

    public void createSimpleDialog(Context a, String title, String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
