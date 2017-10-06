package com.example.slava.bmigraph.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.slava.bmigraph.App;
import com.example.slava.bmigraph.R;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private FragmentActivity mActivity;
    private String[] mUsers;
    private TextView mTextViewLabelCustom;

    public SpinnerAdapter(Context context, int textViewResourceId, FragmentActivity activity) {
        super(context, textViewResourceId, App.getDbManager().getAllUsers());
        mActivity = activity;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getDropDownItemView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getDropDownItemView(int position, View convertView, ViewGroup parent) {

        mUsers = App.getDbManager().getAllUsers();
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView label = row.findViewById(R.id.textView);
        label.setText(getUser(position));

        return row;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        mUsers = App.getDbManager().getAllUsers();
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View row = inflater.inflate(R.layout.spinner_custom_view, parent, false);
        mTextViewLabelCustom = row.findViewById(R.id.textView_custom);
        mTextViewLabelCustom.setText(getUser(position));

        return row;
    }

    private String getUser(int position){

        if (mUsers.length > 0 && position < mUsers.length){
            return mUsers[position];
        } else {
            return "No users";
        }
    }

    public void updateSpinner(String user) {
        mTextViewLabelCustom.setText(user);
    }
}