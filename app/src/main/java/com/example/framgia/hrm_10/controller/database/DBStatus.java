package com.example.framgia.hrm_10.controller.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 20/06/2016.
 */
public class DBStatus {
    private SQLiteOpenHelper mSqLiteOpenHelper;

    public DBStatus(SQLiteOpenHelper sqLiteOpenHelper) {
        this.mSqLiteOpenHelper = sqLiteOpenHelper;
    }

    // Adding new status
    public void addStatus(Status status) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_TYPE_STATUS, status.getTypeStatus());
        db.insert(Settings.TABLE_STATUS, null, values);
        db.close();
    }

    // Getting type single status
    public String getStatus(int id) {
        String type_status = "";
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_STATUS + " WHERE " + Settings.KEY_ID_STATUS + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            type_status = cursor.getString(cursor.getColumnIndex(Settings.KEY_TYPE_STATUS));
        }
        db.close();
        return type_status;
    }

    // Getting All Status
    public List<Status> getAllStatus() {
        List<Status> statusList = new ArrayList<Status>();
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_STATUS;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Status status = new Status(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_TYPE_STATUS))
                );
                statusList.add(status);
            } while (cursor.moveToNext());
        }
        db.close();
        return statusList;
    }

    // Getting typeStatus
    public int getStatus(String typeStatus) {
        int id_status = Settings.ID_NULL;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_STATUS + " WHERE " + Settings.KEY_TYPE_STATUS + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{typeStatus});
        if (cursor != null && cursor.moveToFirst()) {
            id_status = cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS));
        }
        db.close();
        return id_status;
    }
}
