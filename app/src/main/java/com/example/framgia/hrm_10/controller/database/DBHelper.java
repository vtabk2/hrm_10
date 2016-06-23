package com.example.framgia.hrm_10.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.controller.settings.Settings;

/**
 * Created by framgia on 02/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private DBDepartment mDbDepartment;
    private DBStaff mDbStaff;
    private DBStatus mDbStatus;
    private DBAccount mDbAccount;

    public DBHelper(Context context) {
        super(context, Settings.DATABASE_NAME, null, Settings.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateTable(db);
    }

    public DBDepartment getDbDepartment() {
        return mDbDepartment;
    }

    public void createDbDepartment() {
        mDbDepartment = new DBDepartment(this);
    }

    public DBStaff getDbStaff() {
        return mDbStaff;
    }

    public void createDbStaff() {
        mDbStaff = new DBStaff(this);
    }

    public DBStatus getDbStatus() {
        return mDbStatus;
    }

    public void createDbStatus() {
        mDbStatus = new DBStatus(this);
    }

    public DBAccount getDbAccount() {
        return mDbAccount;
    }

    public void createAccount() {
        mDbAccount = new DBAccount(this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Settings.TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + Settings.TABLE_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + Settings.TABLE_STATUS);
        db.execSQL("DROP TABLE IF EXISTS " + Settings.TABLE_ACCOUNT);
        // Create tables again
        onCreate(db);
    }

    private void onCreateTable(SQLiteDatabase db) {
        // table department
        String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + Settings.TABLE_DEPARTMENT + "("
                + Settings.KEY_ID_DEPARTMENT + " INTEGER PRIMARY KEY," + Settings.KEY_NAME_DEPARTMENT + " TEXT,"
                + Settings.KEY_ID_IMAGE_DEPARTMENT + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        // table staff
        String CREATE_TABLE_STAFF = "CREATE TABLE " + Settings.TABLE_STAFF + "("
                + Settings.KEY_ID_STAFF + " INTEGER PRIMARY KEY," + Settings.KEY_NAME_STAFF + " TEXT,"
                + Settings.KEY_PLACE_OF_BIRTH_STAFF + " TEXT," + Settings.KEY_BIRTHDAY_STAFF + " TEXT,"
                + Settings.KEY_PHONE_STAFF + " TEXT," + Settings.KEY_ID_POSITION_IN_COMPANY_STAFF + " INTEGER,"
                + Settings.KEY_ID_STATUS_STAFF + " INTEGER," + Settings.KEY_LEFT_JOB_STAFF + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_STAFF);
        // table status
        String CREATE_TABLE_STATUS = "CREATE TABLE " + Settings.TABLE_STATUS + "("
                + Settings.KEY_ID_STATUS + " INTEGER PRIMARY KEY,"
                + Settings.KEY_TYPE_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_STATUS);
        // table account
        String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + Settings.TABLE_ACCOUNT + "("
                + Settings.KEY_ID_ACCOUNT + " INTEGER PRIMARY KEY,"
                + Settings.KEY_NAME_ACCOUNT + " TEXT,"
                + Settings.KEY_PASS_ACCOUNT + " TEXT,"
                + Settings.KEY_PERMISSION_ACCOUNT + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_ACCOUNT);
    }
}