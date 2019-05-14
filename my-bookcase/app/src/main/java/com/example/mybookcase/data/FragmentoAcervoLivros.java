package com.example.mybookcase.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybookcase.R;
import com.example.mybookcase.data.model.Item;

import java.util.ArrayList;
import java.util.List;

public class FragmentoAcervoLivros extends Fragment {

    View minhaView;

    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Item> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        minhaView = inflater.inflate(R.layout.layout_acervo_livros, container, false);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("As cronicas de Gelo e Fogo","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got1)));
        list.add(new Item("A Fúria dos reis","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got2)));
        list.add(new Item("A Tormenta de Espadas","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got3)));
        list.add(new Item("As cronicas de Gelo e Fogo","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got1)));
        list.add(new Item("A Fúria dos reis","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got2)));
        list.add(new Item("A Tormenta de Espadas","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got3)));
        list.add(new Item("As cronicas de Gelo e Fogo","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got1)));
        list.add(new Item("A Fúria dos reis","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got2)));
        list.add(new Item("A Tormenta de Espadas","Livro de George R. R. Martin",getResources().getDrawable(R.drawable.got3)));

        ItemAdapter itemAdapter = new ItemAdapter(getContext(), list);

        ListView listView = (ListView) minhaView.findViewById(R.id.listAcervoLivros);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ((Item)parent.getItemAtPosition(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        return minhaView;

        /*@SuppressLint("ResourceType") View idStubList = inflater.inflate(R.id.stub_list,container,false);
        @SuppressLint("ResourceType") View idStubGrid = inflater.inflate(R.id.stub_grid,container,false);

        stubList = (ViewStub) idStubList.findViewById(R.id.stub_list);
        stubGrid = (ViewStub) idStubGrid.findViewById(R.id.stub_grid);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) getView().findViewById(R.id.mylistview);
        gridView = (GridView) getView().findViewById(R.id.mygridview);

        //get list of product
        getItemList();

        //Get current view mode in share reference
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ViewMode", Context.MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW);//Default is view listview
        //Register item lick
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);

        switchView();*/
    }

    /*private void switchView() {

        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this, R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        } else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Item> getItemList() {
        //pseudo code to get product, replace your code to get real product here
        productList = new ArrayList<>();
        productList.add(new Item(R.drawable.got1, "Title 1", "This is description 1"));
        productList.add(new Item(R.drawable.got1, "Title 2", "This is description 2"));
        productList.add(new Item(R.drawable.got1, "Title 3", "This is description 3"));
        productList.add(new Item(R.drawable.got1, "Title 4", "This is description 4"));
        productList.add(new Item(R.drawable.got1, "Title 5", "This is description 5"));
        productList.add(new Item(R.drawable.got1, "Title 6", "This is description 6"));
        productList.add(new Item(R.drawable.got1, "Title 7", "This is description 7"));
        productList.add(new Item(R.drawable.got1, "Title 8", "This is description 8"));
        productList.add(new Item(R.drawable.got1, "Title 9", "This is description 9"));
        productList.add(new Item(R.drawable.got1, "Title 10", "This is description 10"));

        return productList;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            Toast.makeText(getContext(), productList.get(position).getName() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_menu_1:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                } else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //Save view mode in share reference
                SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("ViewMode", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }*/

}
