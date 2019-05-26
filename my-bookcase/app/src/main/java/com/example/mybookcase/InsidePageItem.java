package com.example.mybookcase;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybookcase.data.Helper.MsgHelper;
import com.example.mybookcase.data.model.Item;

public class InsidePageItem extends AppCompatActivity {

    TextView itemName;
    ImageView itemCover;
    TextView itemDescription;
    TextView itemVerMais;
    String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_page_item);

        itemCover = findViewById(R.id.itemCover);
        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);
        itemVerMais = findViewById(R.id.itemVerMais);

        final Bundle args = getIntent().getBundleExtra("args");

        if(args != null){
            itemName.setText(args.getString("nome"));
            if(args.getByteArray("image") != null){
                Drawable img = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(args.getByteArray("image"),0,args.getByteArray("image").length));
                itemCover.setImageDrawable(img);
            }
            descricao = args.getString("descricao");
            System.out.println(descricao);
            itemDescription.setText(args.getString("descricao"));
        }

        itemVerMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MsgHelper().createSimpleDialog(InsidePageItem.this, "Descrição do Filme", descricao);
            }
        });

    }
}
