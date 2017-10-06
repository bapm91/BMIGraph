package com.example.slava.bmigraph.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.slava.bmigraph.App;
import com.example.slava.bmigraph.R;
import com.example.slava.bmigraph.db.DatabaseHelper;
import com.jjoe64.graphview.GraphView;

import java.util.regex.Pattern;

public class WeightGraphFragment extends GraphFragment {
    {
        Log.e("@@@", " ######"+ WeightGraphFragment.this.toString());
    }

    public static WeightGraphFragment getInstance() {
        Bundle args = new Bundle();
        WeightGraphFragment fragment = new WeightGraphFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean isInputRight() {

        if (mEditText.getText().toString().length() == 0) {
            Toast.makeText(getContext(),
                    getString(R.string.weight_et_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Pattern.matches(getString(R.string.regex_graph_weight)
                , mEditText.getText().toString())) {
            Toast.makeText(getContext(),
                    getString(R.string.graph_weight_wrong), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_weight_graph;
    }

    @Override
    protected void findViews(View view) {
        mButton = view.findViewById(R.id.bt_add_weight);
        mGraphView = view.findViewById(R.id.graph_weight);
        mEditText = view.findViewById(R.id.et_graph_weight);
        mSpinner = view.findViewById(R.id.spinner_weight);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void insertData() {
        if (isInputRight()) {
            App.getDbManager().insertData(
                    App.getDbManager().getAllUsers()[mSpinner.getSelectedItemPosition()],
                    Integer.valueOf(mEditText.getText().toString()), -1);
        }
    }

    @Override
    protected String getDataColumnName() {
        return DatabaseHelper.COL_4;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}