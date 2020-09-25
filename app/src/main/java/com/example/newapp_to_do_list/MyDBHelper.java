package com.example.newapp_to_do_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.newapp_to_do_list.model.pojo.Plans;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String dbname = "plan.db";
    public static final int version = 1;
    public static final String TableName = "plans";
    Context cnt;

    public MyDBHelper(Context context) {

        super(context, dbname, null, version);
        this.cnt = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table plans (id integer primary key autoincrement, name text, fromdate text, todate text, currenttime text, timepicked text, abouttask text) ";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS plans");
        onCreate(db);

    }


    public String insertPlanData(Plans plans) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", plans.getName());
        contentValues.put("fromdate", plans.getFromdate());
        contentValues.put("todate", plans.getTodate());
        contentValues.put("currenttime", plans.getCurrenttime());
        contentValues.put("timepicked", plans.getTimepicked());
        contentValues.put("abouttask", plans.getAbouttask());
        long res = db.insert("plans", null, contentValues);

        db.close();

        if (res == -1) {

            return "Data not inserted";
        } else {
            return "Data Successfully inserted";

        }

    }

    public List<String> getDBData(int id) {
        List<String> plansList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TableName + " WHERE id = " + id;
        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor != null) {
            cursor.moveToFirst();

            do {
                Plans plans = new Plans();
                plansList.add(cursor.getString(1));
                plansList.add(cursor.getString(2));
                plansList.add(cursor.getString(3));
                plansList.add(cursor.getString(4));
                plansList.add(cursor.getString(5));
                plansList.add(cursor.getString(6));

                plansList.add(String.valueOf(plans));

            } while (cursor.moveToNext());
        }
        return plansList;
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteItem(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TableName + " where id='" + get_ID );
    }

    public List<String> getFromDates(int id) {
        List<String> plansList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TableName + " WHERE id = " + id;
        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor != null) {
            cursor.moveToFirst();

            do {
                Plans plans = new Plans();
                plansList.add(cursor.getString(1));
                plansList.add(cursor.getString(2));
                plansList.add(cursor.getString(3));
                plansList.add(cursor.getString(4));
                plansList.add(cursor.getString(5));
                plansList.add(cursor.getString(6));

                plansList.add(String.valueOf(plans));

            } while (cursor.moveToNext());
        }
        return plansList;
    }

    }


