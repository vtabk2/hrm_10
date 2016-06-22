package com.example.framgia.hrm_10.controller.database;

import android.content.ContentValues;
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

    // Adding new department
    public boolean addAccount(Account account) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_NAME_ACCOUNT, account.getName());
        values.put(Settings.KEY_PASS_ACCOUNT, account.getPass());
        values.put(Settings.KEY_PERMISSION_ACCOUNT, account.getPermission());
        long check = db.insert(Settings.TABLE_ACCOUNT, null, values);
        db.close();
        return check >= Settings.CHECK_ADD_TRUE;
    }

    public int login(String name, String pass) {
        int permission = Settings.PERMISSION_DEFAULT;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_ACCOUNT + " WHERE " + Settings.KEY_NAME_ACCOUNT + "=?"
                + " AND " + Settings.KEY_PASS_ACCOUNT + " =?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{name, pass});
        if (cursor != null && cursor.moveToFirst()) {
            permission = cursor.getInt(cursor.getColumnIndex(Settings.KEY_PERMISSION_ACCOUNT));
        }
        db.close();
        return permission;
    }
}
