package com.example.slava.bmigraph.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.slava.bmigraph.App;
import com.example.slava.bmigraph.R;
import com.example.slava.bmigraph.db.DatabaseHelper;

import java.util.regex.Pattern;

public class HeightGraphFragment extends GraphFragment {
    {
        Log.e("@@@", "######"+HeightGraphFragment.this.toString());
    }

    public static HeightGraphFragment getInstance() {
        Bundle args = new Bundle();
        HeightGraphFragment fragment = new HeightGraphFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean isInputRight() {

        if (mEditText.getText().toString().length() == 0) {
            Toast.makeText(getContext(),
                    getString(R.string.height_et_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Pattern.matches(getString(R.string.regex_graph_height)
                , mEditText.getText().toString())) {
            Toast.makeText(getContext(),
                    getString(R.string.graph_height_wrong), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_height_graph;
    }

    @Override
    protected void findViews(View view) {
        mButton = view.findViewById(R.id.bt_add_height);
        mGraphView = view.findViewById(R.id.graph_height);
        mEditText = view.findViewById(R.id.et_graph_height);
        mSpinner = view.findViewById(R.id.spinner_height);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void insertData() {
        if (isInputRight()) {
            App.getDbManager().insertData(
                    App.getDbManager().getAllUsers()[mSpinner.getSelectedItemPosition()]
                    , -1, Integer.valueOf(mEditText.getText().toString()));
        }
    }

    @Override
    protected String getDataColumnName() {
        return DatabaseHelper.COL_5;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
