package com.example.mybookcase.data;

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

import java.util.ArrayList;

public class FragmentoAcervoFilmes extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.layout_acervo_filmes, container, false);

        ArrayList<Item> list = new ArrayList<>();

        list.add(new Item("Avengeiros 1","Filme da Marvel",getResources().getDrawable(R.drawable.avg1)));
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
        list.add(new Item("Harry Potter e a pedra filosofal","Filme da Warner",getResources().getDrawable(R.drawable.hp1)));

        ItemAdapter itemAdapter = new ItemAdapter(getContext(), list);

        ListView listView = (ListView) view.findViewById(R.id.listAcervoFilmes);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ((Item)parent.getItemAtPosition(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
