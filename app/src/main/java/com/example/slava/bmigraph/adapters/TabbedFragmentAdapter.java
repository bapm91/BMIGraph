package com.example.slava.bmigraph.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.slava.bmigraph.fragments.BMIFragment;
import com.example.slava.bmigraph.fragments.HeightGraphFragment;
import com.example.slava.bmigraph.fragments.WeightGraphFragment;

import java.util.List;

public class TabbedFragmentAdapter extends FragmentPagerAdapter {
    private List<String> mList;

    public TabbedFragmentAdapter(FragmentManager fragmentManager, List<String> mList) {
        super(fragmentManager);
        this.mList = mList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return BMIFragment.getInstance();
            case 1:
                return WeightGraphFragment.getInstance();
            case 2:
                return HeightGraphFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}