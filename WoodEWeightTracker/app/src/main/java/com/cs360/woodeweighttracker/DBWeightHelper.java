package com.cs360.woodeweighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBWeightHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Weight.db";
    public DBWeightHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table weights(date TEXT primary key, weight TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists weights");
    }

    public Boolean insertWeightData(String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("weight", weight);
        long result = db.insert("weights", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean updateWeightData(String date, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("weight", weight);
        Cursor cursor = db.rawQuery("select * from weights where date = ?", new String[] {date});
        if (cursor.getCount() > 0) {
            long result = db.update("weights", contentValues, "date=?", new String[] {date});
            return result != -1;
        }
        else {
            return false;
        }
    }

    public Boolean deleteWeightData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from weights where date = ?", new String[] {date});
        if (cursor.getCount() > 0) {
            long result = db.delete("weights", "date=?", new String[] {date});
            return result != -1;
        }
        else {
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from weights", null);
        return cursor;
    }

}
