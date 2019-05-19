package com.example.mybookcase.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import java.util.List;

public class FragmentoAcervoFilmes extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.layout_acervo_filmes, container, false);
        ArrayList<Item> list = new ArrayList<>();

        try{
            ItemDAO itemDAO = new ItemDAO(getContext());
            list = itemDAO.getItens();
        }catch(Exception e){
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

            ListView listView = (ListView) view.findViewById(R.id.listAcervoFilmes);
            listView.setAdapter(itemAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), ((Item)parent.getItemAtPosition(position)).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        /*list.add(new Item("Avengeiros 1","Filme da Marvel",getResources().getDrawable(R.drawable.avg1)));
        list.add(new Item("Avengeiros 2","Filme da Marvel",getResources().getDrawable(R.drawable.avg2)));
        list.add(new Item("Harry Potter e a pedra filosofal","Filme da Warner",getResources().getDrawable(R.drawable.hp1)));
        list.add(new Item("Avengeiros 1","Filme da Marvel",getResources().getDrawable(R.drawable.avg1)));
        list.add(new Item("Avengeiros 2","Filme da Marvel",getResources().getDrawable(R.drawable.avg2)));
        list.add(new Item("Harry Potter e a pedra filosofal","Filme da Warner",getResources().getDrawable(R.drawable.hp1)));
        list.add(new Item("Avengeiros 1","Filme da Marvel",getResources().getDrawable(R.drawable.avg1)));
        list.add(new Item("Avengeiros 2","Filme da Marvel",getResources().getDrawable(R.drawable.avg2)));
        list.add(new Item("Harry Potter e a pedra filosofal","Filme da Warner",getResources().getDrawable(R.drawable.hp1)));
        list.add(new Item("Avengeiros 1","Filme da Marvel",getResources().getDrawable(R.drawable.avg1)));
        list.add(new Item("Avengeiros 2","Filme da Marvel",getResources().getDrawable(R.drawable.avg2)));
        list.add(new Item("Harry Potter e a pedra filosofal","Filme da Warner",getResources().getDrawable(R.drawable.hp1)));*/

        return view;
    }

}
