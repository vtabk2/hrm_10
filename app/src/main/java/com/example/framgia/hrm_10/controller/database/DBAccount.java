package com.example.framgia.hrm_10.controller.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Account;

/**
 * Created by framgia on 22/06/2016.
 */
public class DBAccount {
    private SQLiteOpenHelper mSqLiteOpenHelper;

    public DBAccount(SQLiteOpenHelper sqLiteOpenHelper) {
        this.mSqLiteOpenHelper = sqLiteOpenHelper;
    }

    public Account login(String name, String pass) {
        Account account = null;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_ACCOUNT
                + " WHERE " + Settings.KEY_NAME_ACCOUNT + "=?"
                + " AND " + Settings.KEY_PASS_ACCOUNT + " =?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name, pass});
        if (cursor != null && cursor.moveToFirst()) {
            account = new Account(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_ACCOUNT)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_ACCOUNT)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_PASS_ACCOUNT)),
                    cursor.getInt(cursor.getColumnIndex(Settings.KEY_PERMISSION_ACCOUNT)));
        }
        db.close();
        return account;
    }
}
