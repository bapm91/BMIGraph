package com.example.slava.bmigraph;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.slava.bmigraph.adapters.TabbedFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTabbedActivity();
    }

    private void initTabbedActivity() {
        List<String> list = new ArrayList<>();
        list.add(getString(R.string.title_bmi_fragment));
        list.add(getString(R.string.title_weight_fragment));
        list.add(getString(R.string.title_height_fragment));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        TabbedFragmentAdapter tabbed =
                new TabbedFragmentAdapter(getSupportFragmentManager(), list);

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(tabbed);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
