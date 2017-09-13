package com.example.slava.bmigraph.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slava.bmigraph.R;

public class WeightGraphFragment extends Fragment {

    public static WeightGraphFragment getInstance() {
        Bundle args = new Bundle();
        WeightGraphFragment fragment = new WeightGraphFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight_graph, container, false);
        return view;
    }
}
