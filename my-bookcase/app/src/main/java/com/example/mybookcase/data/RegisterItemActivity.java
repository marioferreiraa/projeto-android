package com.example.mybookcase.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mybookcase.R;
import com.example.mybookcase.data.Exceptions.ValidateException;
import com.example.mybookcase.data.facade.RegistrationFacade;
import com.example.mybookcase.data.model.Item;

public class RegisterItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_item);

        final EditText fieldName = findViewById(R.id.editName);
        final EditText fieldDesc = findViewById(R.id.editDesc);
        final EditText fieldType = findViewById(R.id.editType);
        final EditText fieldPath = findViewById(R.id.editPath);
        final Button buttonRegister = findViewById(R.id.buttonRegisterItem);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = fieldName.getText().toString();
                String desc = fieldDesc.getText().toString();
                String type = fieldType.getText().toString();
                String pathImage = fieldPath.getText().toString();


                    Item item = new Item(null, name, desc, type, pathImage, "Y");
                RegistrationFacade rF = new RegistrationFacade(getApplicationContext(), RegisterItemActivity.this);
                try {
                    rF.insertItem(item);
                } catch (ValidateException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
