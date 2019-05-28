package com.example.mybookcase.data;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.Helper.MsgHelper;
import com.example.mybookcase.data.persistence.ItemDAO;

public class InsidePageItem extends AppCompatActivity {

    TextView itemName;
    ImageView itemCover;
    TextView itemDescription;
    TextView itemVerMais;
    String descricao;
    ImageButton deletar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_page_item);

        itemCover = findViewById(R.id.itemCover);
        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);
        itemVerMais = findViewById(R.id.itemVerMais);
        deletar = (ImageButton) findViewById(R.id.deletar);

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

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(InsidePageItem.this);
                View mView = getLayoutInflater().inflate(R.layout.layout_modal_excluir, null);

                final TextView titulo = mView.findViewById(R.id.tituloItem);
                final ImageView imagem = mView.findViewById(R.id.imageItem);
                Button btnDeletar = (Button) mView.findViewById(R.id.btnDeletar);
                Button btnCancelar = (Button) mView.findViewById(R.id.btnCancelar);

                titulo.setText(args.getString("nome"));
                Drawable imgModal = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(args.getByteArray("image"),0,args.getByteArray("image").length));
                imagem.setImageDrawable(imgModal);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                btnDeletar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemDAO itemDAO = new ItemDAO(getApplicationContext());
                        itemDAO.deleteItem(args.getInt("id"),args.getString("isAcervo"), args.getString("type"));
                        Toast.makeText(InsidePageItem.this,"Item deletado com sucesso! ", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();
                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

    }
}
