package com.example.mybookcase.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> list;

    public ItemAdapter(Context context, ArrayList<Item> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Item currentItem = list.get(position);
        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.titulo = convertView.findViewById(R.id.txtTitle);
            holder.desc = convertView.findViewById(R.id.txtDescription);
            holder.img = convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        holder.titulo.setText(currentItem.getName());
        holder.desc.setText(currentItem.getDescription());
        holder.img.setImageDrawable(currentItem.getTestImage());

        return convertView;
    }

    private static class ViewHolder {
        TextView titulo;
        TextView desc;
        ImageView img;
    }


}
