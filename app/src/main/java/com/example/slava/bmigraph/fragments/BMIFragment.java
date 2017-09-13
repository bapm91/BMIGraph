package com.example.slava.bmigraph.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slava.bmigraph.R;

public class BMIFragment extends Fragment {

    public static BMIFragment getInstance() {
        Bundle args = new Bundle();
        BMIFragment fragment = new BMIFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);
        return view;
    }
}
