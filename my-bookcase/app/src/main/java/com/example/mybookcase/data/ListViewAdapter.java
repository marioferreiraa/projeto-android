package com.example.mybookcase.data;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NgocTri on 10/22/2016.
 */

public class ListViewAdapter extends ArrayAdapter<Item> {

    public ListViewAdapter(FragmentoAcervoLivros context, int resource, List<Item> objects) {
        super(context.getContext(),resource,objects);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }
        Item item = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);

        img.setImageResource(item.getImageId());
        txtTitle.setText(item.getName());
        txtDescription.setText(item.getDescription());

        //ImageButton delete = (ImageButton) v.findViewById(R.id.deletar);

        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getItem(position).getId().toString(), Toast.LENGTH_LONG).show();
            }
        });*/

        return v;
    }
}
