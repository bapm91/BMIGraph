package com.example.slava.bmigraph;

import android.app.Application;

import com.example.slava.bmigraph.db.DbManager;

public class App extends Application {

    private static DbManager mDbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mDbManager = new DbManager();
        mDbManager.init(this);
    }

    public static DbManager getDbManager() {
        return mDbManager;
    }
}
