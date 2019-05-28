package com.example.mybookcase.data;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.persistence.ItemDAO;

import java.util.ArrayList;

public class FragmentoAcervoFilmes extends Fragment {

    View view;
    private ArrayList<Item> lista;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.layout_acervo_filmes, container, false);
        FloatingActionButton addAcervoFilme = view.findViewById(R.id.addAcervoFilme);

        getItens();

        addAcervoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle details = new Bundle();
                details.putString("type", "movie");
                details.putString("isAcervo", "s");
                Intent intent = new Intent(getContext(),RegisterItemActivity.class);
                intent.putExtra("details", details);
                startActivity(intent);
            }
        });

        return view;

    }

    public void getItens(){
        try{
            ItemDAO itemDAO = new ItemDAO(getContext());
            lista = new ArrayList<>();
            lista = itemDAO.getPersonItens("s", "movie");
        }catch(Exception e){
            e.printStackTrace();
        }

        if(lista.size() > 0){

            for(Item item : lista){
                if(item.getImageByte() == null){
                    item.setImage(getResources().getDrawable(R.drawable.no_image_default));
                }else{
                    Drawable img = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(item.getImageByte(),0,item.getImageByte().length));
                    item.setImage(img);
                }
            }

            itemAdapter = new ItemAdapter(getContext(), lista);

            ListView listView = (ListView) view.findViewById(R.id.listAcervoFilmes);
            listView.setAdapter(itemAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Item item = (Item) parent.getItemAtPosition(position);

                    Bundle args = new Bundle();
                    args.putInt("id",item.getId());
                    args.putString("nome", item.getName());
                    args.putString("descricao", item.getDescription());
                    args.putString("type", item.getType());
                    args.putString("isAcervo", item.getIsAcervo());
                    args.putByteArray("image", item.getImageByte());

                    Intent intent = new Intent(getContext(), InsidePageItem.class);
                    intent.putExtra("args", args);
                    startActivity(intent);
                }
            });

        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getItens();
    }

}
