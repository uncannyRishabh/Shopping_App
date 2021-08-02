package com.cse.shoppingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class home_frag extends Fragment {
    private FirebaseAuth mAuth;

    private ImageSlider imageSlider;

    public home_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<SlideModel> list = new ArrayList<>();
        list.add(new SlideModel(R.drawable.ad_slide_0, ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.ad_slide_1, ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.ad_slide_2, ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.ad_slide_3, ScaleTypes.FIT));
        imageSlider = view.findViewById(R.id.imageSlider);
        imageSlider.setImageList(list);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_frag, container, false);
    }


}