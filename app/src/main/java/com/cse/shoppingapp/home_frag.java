package com.cse.shoppingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class home_frag extends Fragment {

    public home_frag() {
        // Required empty public constructor
    }

    public static home_frag newInstance(String param1, String param2) {
        home_frag fragment = new home_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_frag, container, false);
    }


}