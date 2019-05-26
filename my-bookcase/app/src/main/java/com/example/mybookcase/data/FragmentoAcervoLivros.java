package com.example.mybookcase.data;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybookcase.InsidePageItem;
import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;
import com.example.mybookcase.data.persistence.ItemDAO;

import java.util.ArrayList;
import java.util.List;

public class FragmentoAcervoLivros extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.layout_acervo_livros, container, false);

        ArrayList<Item> list = new ArrayList<>();

        try{
            ItemDAO itemDAO = new ItemDAO(getContext());
            list = itemDAO.getPersonItens("s","book");
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

            ListView listView = (ListView) view.findViewById(R.id.listAcervoLivros);
            listView.setAdapter(itemAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), ((Item)parent.getItemAtPosition(position)).getId().toString(), Toast.LENGTH_SHORT).show();
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

        return view;

    }

}
