package com.list.todo.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.list.todo.todolist.data.Data;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "sticker";
    private static final String ID = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "body";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1 + " TEXT," +
                COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String title, String body) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, title);
        contentValues.put(COL2, body);

        Log.d(TAG, "ADDED? " + title + body + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public List<Data> stickerData() {

        List<Data> dataList = new ArrayList<>();

        String STICKER_DETAIL_SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(STICKER_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Data data = new Data();
                    data.title = cursor.getString(cursor.getColumnIndex(COL1));
                    data.describe = cursor.getString(cursor.getColumnIndex(COL2));

                    dataList.add(data);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

            return dataList;

        }

//    public Cursor getData(){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String select_all = "SELECT * FROM " + TABLE_NAME;
//        @SuppressLint("Recycle")
//        Cursor data = db.rawQuery(select_all, null);
//        return data;
//
//        }

    }
}
