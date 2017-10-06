package com.example.slava.bmigraph.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.slava.bmigraph.App;
import com.example.slava.bmigraph.R;
import com.example.slava.bmigraph.adapters.TabsFragmentAdapter;
import com.example.slava.bmigraph.db.DatabaseHelper;
import com.example.slava.bmigraph.fragments.GraphFragment;
import com.example.slava.bmigraph.fragments.HeightGraphFragment;
import com.example.slava.bmigraph.fragments.WeightGraphFragment;
import com.example.slava.bmigraph.interfaces.OnGraphSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnGraphSelectedListener {

    private TabsFragmentAdapter mTabsFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToolbar();
        initTabbedActivity();
    }

    private void addToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabbedActivity() {
        List<String> list = new ArrayList<>();
        list.add(getString(R.string.title_bmi_fragment));
        list.add(getString(R.string.title_weight_fragment));
        list.add(getString(R.string.title_height_fragment));

        mTabsFragmentAdapter =
                new TabsFragmentAdapter(getSupportFragmentManager(), list);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(mTabsFragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_user) {
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("New user!")
                    .setCancelable(false)
                    .setView(input)
                    .setPositiveButton("ОК",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    App.getDbManager().insertData(input.getText().toString(),
                                            -1, -1);
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArticleSelected(int position, GraphFragment fragmentClass) {
        if (fragmentClass instanceof HeightGraphFragment) {
            WeightGraphFragment weightFragment = (WeightGraphFragment) mTabsFragmentAdapter.getItem(1);
            Log.e("@@@", weightFragment.toString());
//            weightFragment.updateArticleView(position);
        } else if (fragmentClass instanceof WeightGraphFragment) {
            HeightGraphFragment heightFragment = (HeightGraphFragment) mTabsFragmentAdapter.getItem(2);
            Log.e("@@@", heightFragment.toString());
//            heightFragment.updateArticleView(position);
        }
    }
}
