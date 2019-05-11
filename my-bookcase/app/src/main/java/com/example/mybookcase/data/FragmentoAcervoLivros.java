package com.example.mybookcase.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybookcase.R;

public class FragmentoAcervoLivros extends Fragment {

    View minhaView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        minhaView = inflater.inflate(R.layout.layout_acervo_livros, container, false);
        return minhaView;
    }

}
