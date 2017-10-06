package com.example.slava.bmigraph.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.slava.bmigraph.App;
import com.example.slava.bmigraph.R;
import com.example.slava.bmigraph.activity.MainActivity;
import com.example.slava.bmigraph.adapters.SpinnerAdapter;
import com.example.slava.bmigraph.interfaces.OnGraphSelectedListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public abstract class GraphFragment extends Fragment
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    protected Button mButton;
    protected Spinner mSpinner;
    protected EditText mEditText;
    protected GraphView mGraphView;
    protected String[] users;
    protected String user;
    protected SpinnerAdapter mSpinnerAdapter;
    protected OnGraphSelectedListener mCallback;

    protected abstract int getLayout();

    protected abstract void findViews(View view);

    protected abstract void insertData();

    protected abstract String getDataColumnName();

    protected abstract boolean isInputRight();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnGraphSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        findViews(view);
        setUsers();
        setGraphView(new DataPoint[]{});
        setOnClick();
        setSpinnerAdapter();

        return view;
    }

    protected void setUsers() {
        users = App.getDbManager().getAllUsers();
    }

    protected void setOnClick() {
        mButton.setOnClickListener(this);
    }

    protected int[] getUsersData(String databaseColumn) {
        return App.getDbManager().getUsersData(user, databaseColumn);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (users.length > 0) {
            user = users[i];
            int[] usersData = getUsersData(getDataColumnName());
            setGraphView(getDataPoints(usersData));

//            mCallback.onArticleSelected(i, this);
        }
    }

    protected DataPoint[] getDataPoints(int[] usersData) {
        int length;
        if (usersData.length > 9) {
            length = 9;
        } else {
            length = usersData.length;
        }

        DataPoint[] dataPoints = new DataPoint[length];

        for (int i = length - 1; i >= 0; i--) {
            dataPoints[i] = new DataPoint(i, usersData[usersData.length - (length - i)]);
        }
        return dataPoints;
    }

    protected void setSpinnerAdapter() {
        mSpinnerAdapter = new SpinnerAdapter(getContext(),
                R.layout.spinner_row, getActivity());

        mSpinner.setAdapter(mSpinnerAdapter);
    }

    protected void setGraphView(DataPoint[] dataPoints) {
        mGraphView.getGridLabelRenderer().setHorizontalLabelsAngle(9);
        mGraphView.getViewport().setXAxisBoundsManual(true);
        mGraphView.getViewport().setMinX(0);
        mGraphView.getViewport().setMaxX(8);
        mGraphView.setBackgroundColor(getResources().getColor(R.color.white_dark));

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        mGraphView.removeAllSeries();
        mGraphView.addSeries(series);
    }

    @Override
    public void onClick(View view) {
        if (App.getDbManager().getAllUsers().length == 0){
            Toast.makeText(getContext(), getString(R.string.no_any_users), Toast.LENGTH_SHORT).show();
            return;
        }
        if (isInputRight()) {
            insertData();
            setUsers();
            setGraphView(getDataPoints(getUsersData(getDataColumnName())));
            mEditText.setText("");
//            mCallback.onArticleSelected(mSpinner.getSelectedItemPosition(), this);
        }
    }

    public void updateArticleView(int position) {
        setUsers();
        user = users[position];
//        setSpinnerAdapter();
//        mSpinnerAdapter.updateSpinner(user);
//        mSpinner.setAdapter(mSpinnerAdapter);
    }
}