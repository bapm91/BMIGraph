package com.example.slava.bmigraph.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slava.bmigraph.R;

import java.util.regex.Pattern;

public class BMIFragment extends Fragment {

    private EditText mEtEnterHeight;
    private EditText mEtEnterWeight;
    private TextView mTvResult;
    private Button mBtRotate;
    private double mHumanHeight;
    private double mHumanWeight;

    public static BMIFragment getInstance() {
        Bundle args = new Bundle();
        BMIFragment fragment = new BMIFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        findViews(view);

        mBtRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isInputRight()) {

                    mHumanHeight = Double.valueOf(mEtEnterHeight.getText().toString());
                    mHumanWeight = Double.valueOf(mEtEnterWeight.getText().toString());

                    mTvResult.setText(getString(R.string.result_format
                            , mHumanWeight
                            , mHumanHeight
                            , findMassIndex(mHumanWeight, mHumanHeight)));
                    mEtEnterHeight.setText("");
                    mEtEnterWeight.setText("");
                }
            }
        });
        return view;
    }

    private boolean isInputRight() {
        if (mEtEnterHeight.getText().toString().length() == 0
                || mEtEnterWeight.getText().toString().length() == 0) {
            Toast.makeText(getContext(),
                    getString(R.string.some_et_empty), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Pattern.matches(getString(R.string.regex_height)
                , mEtEnterHeight.getText().toString())) {
            Toast.makeText(getContext(),
                    getString(R.string.height_wrong), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Pattern.matches(getString(R.string.regex_weight)
                , mEtEnterWeight.getText().toString())) {
            Toast.makeText(getContext(),
                    getString(R.string.weight_wrong), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void findViews(View view) {
        mEtEnterHeight = view.findViewById(R.id.enter_height);
        mEtEnterWeight = view.findViewById(R.id.enter_weight);
        mTvResult = view.findViewById(R.id.result_bmi);
        mBtRotate = view.findViewById(R.id.rotate_bmi);
    }

    private String findMassIndex(double mass, double height) {
        double massIndex = Math.round(100 * mass / Math.pow(height / 100, 2)) / 100.0;
        return getString(R.string.bmi_result_format, massIndex, verdict(massIndex));
    }

    private String verdict(double massIndex) {
        if (massIndex <= 15) {
            return getString(R.string.underweight3);
        }
        if (massIndex > 15 && massIndex <= 16) {
            return getString(R.string.underweight2);
        }
        if (massIndex > 16 && massIndex <= 19.5) {
            return getString(R.string.underweight3);
        }
        if (massIndex > 19.5 && massIndex <= 25) {
            return getString(R.string.normal);
        }
        if (massIndex > 25 && massIndex <= 30) {
            return getString(R.string.overweight);
        }
        if (massIndex > 30 && massIndex <= 35) {
            return getString(R.string.obese1);
        }
        if (massIndex > 35 && massIndex <= 40) {
            return getString(R.string.obese2);
        } else {
            return getString(R.string.obese3);
        }
    }
}
