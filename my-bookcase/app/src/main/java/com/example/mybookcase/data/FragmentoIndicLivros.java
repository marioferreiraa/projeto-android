package com.example.mybookcase.data;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.persistence.ItemDAO;

import java.util.ArrayList;

public class FragmentoIndicLivros extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.layout_indic_livros, container, false);
        FloatingActionButton addIndicLivro = view.findViewById(R.id.addIndicLivro);
        ArrayList<Item> list = new ArrayList<>();

        try{
            ItemDAO itemDAO = new ItemDAO(getContext());
            list = itemDAO.getPersonItens("n", "book");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(list.size() > 0){
            for(Item item : list){
                if(item.getImageByte() == null){
                    item.setImage(getResources().getDrawable(R.drawable.no_image_default));
                }else{
                    Drawable img = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(item.getImageByte(),0,item.getImageByte().length));
                    item.setImage(img);
                }
            }

            ItemAdapter itemAdapter = new ItemAdapter(getContext(), list);

            ListView listView = (ListView) view.findViewById(R.id.listIndicLivros);
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

        addIndicLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle details = new Bundle();
                details.putString("type", "book");
                details.putString("isAcervo", "n");
                Intent intent = new Intent(getContext(),RegisterItemActivity.class);
                intent.putExtra("details", details);
                startActivity(intent);
            }
        });

        return view;
    }

}
