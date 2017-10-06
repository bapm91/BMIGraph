package com.example.slava.bmigraph.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Calendar;

public class DbManager {

    private SQLiteDatabase mDataBase;

    public void init(Context context) {
        mDataBase = new DatabaseHelper(context).getWritableDatabase();
    }

    public void insertData(String user, int weight, int height) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, user);
        contentValues.put(DatabaseHelper.COL_3, getDateString());
        contentValues.put(DatabaseHelper.COL_4, weight);
        contentValues.put(DatabaseHelper.COL_5, height);
        mDataBase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData() {
        return mDataBase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
    }

    public int getId(String user, String date) {
        String sql = "select " + DatabaseHelper.COL_1
                + " from " + DatabaseHelper.TABLE_NAME
                + " where users =? " + "and "
                + DatabaseHelper.COL_3 + " =?";

        Cursor cursor = mDataBase.rawQuery(sql, new String[]{user, date});
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1));

        cursor.close();
        return id;
    }

    public int[] getUsersData(String user, String databaseColumn){
        Log.e("###", "user = " + user + " data = " + databaseColumn, null);
        Cursor cursor = mDataBase.rawQuery("SELECT " + databaseColumn
                + " FROM " + DatabaseHelper.TABLE_NAME
                + " WHERE " + DatabaseHelper.COL_2 + " =? AND (NOT " + databaseColumn +" =?)"
                , new String[]{user, String.valueOf(-1)});

        int[] data = new int[cursor.getCount()];
        cursor.moveToFirst();

        for (int i = 0; i < data.length; i++){
            data[i] = cursor.getInt(cursor.getColumnIndex(databaseColumn));
            if (!cursor.isLast()){
                cursor.moveToNext();
            }
        }

        cursor.close();
        return data;
    }

    public String[] getAllUsers(){
        Cursor cursor = mDataBase.rawQuery("select distinct " + DatabaseHelper.COL_2
                + " from " + DatabaseHelper.TABLE_NAME, null);
        String[] data = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int i = 0; i < data.length; i++){
            data[i] = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2));
            if (!cursor.isLast()){
                cursor.moveToNext();
            }
        }
        cursor.close();
        return data;
    }

    private String getDateString(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return calendar.get(Calendar.DAY_OF_MONTH) + "."
                + month + "."
                +calendar.get(Calendar.YEAR);
    }

    public void delete(int id){
        mDataBase.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_1 + " = " + id, null);
    }

    public void update(int id, int height, int weight){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_4, height);
        contentValues.put(DatabaseHelper.COL_5, weight);
        mDataBase.update(DatabaseHelper.TABLE_NAME,
                contentValues,
                DatabaseHelper.COL_1 + " = " + id,
                null);
    }

    public String getAllDataToString(){
        String result = "";
        Cursor cursor = getAllData();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            result += cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1)) + " ";
            result += cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)) + " ";
            result += cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)) + " ";
            result += cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_4)) + " ";
            result += cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_5)) + " ";
            result += "\n";
            cursor.moveToNext();
        }
        cursor.close();
        return result;
    }
}
