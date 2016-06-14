package com.example.framgia.hrm_10.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.model.Departments;
import com.example.framgia.hrm_10.model.Staff;
import com.example.framgia.hrm_10.model.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 02/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "staffsManagerX";
    // Staffs table name
    private static final String TABLE_STAFF = "staffX";
    // Department table name
    private static final String TABLE_DEPARTMENT = "departmentX";
    // Status table name
    private static final String TABLE_STATUS = "statusX";
    // DEPARTMENT Table Columns names
    private static final String KEY_ID_DEPARTMENT = "id";
    private static final String KEY_NAME_DEPARTMENT = "name";
    private static final String KEY_ID_IMAGE_DEPARTMENT = "image";
    // STAFF Table Columns names
    private static final String KEY_ID_STAFF = "id";
    private static final String KEY_NAME_STAFF = "name";
    private static final String KEY_PLACE_OF_BIRTH_STAFF = "placeOfBirth";
    private static final String KEY_BIRTHDAY_STAFF = "birthday";
    private static final String KEY_PHONE_STAFF = "phone";
    private static final String KEY_ID_POSITION_IN_COMPANY_STAFF = "idPositionInCompany";
    private static final String KEY_ID_STATUS_STAFF = "idStatus";
    private static final String KEY_LEFT_JOB_STAFF = "letJob";
    // STATUS
    private static final String KEY_ID_STATUS = "id";
    private static final String KEY_TYPE_STATUS = "type";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        onCreateTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATUS);
        // Create tables again
        onCreate(db);
    }

    private void onCreateTable(SQLiteDatabase db) {
        // table department
        String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "("
                + KEY_ID_DEPARTMENT + " INTEGER PRIMARY KEY," + KEY_NAME_DEPARTMENT + " TEXT,"
                + KEY_ID_IMAGE_DEPARTMENT + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        // table staff
        String CREATE_TABLE_STAFF = "CREATE TABLE " + TABLE_STAFF + "("
                + KEY_ID_STAFF + " INTEGER PRIMARY KEY," + KEY_NAME_STAFF + " TEXT,"
                + KEY_PLACE_OF_BIRTH_STAFF + " TEXT," + KEY_BIRTHDAY_STAFF + " TEXT,"
                + KEY_PHONE_STAFF + " TEXT," + KEY_ID_POSITION_IN_COMPANY_STAFF + " INTEGER,"
                + KEY_ID_STATUS_STAFF + " INTEGER," + KEY_LEFT_JOB_STAFF + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_STAFF);
        // table status
        String CREATE_TABLE_STATUS = "CREATE TABLE " + TABLE_STATUS + "("
                + KEY_ID_STATUS + " INTEGER PRIMARY KEY,"
                + KEY_TYPE_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_STATUS);
    }

    // Department
    // Adding new department
    public void addDepartment(Departments departments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_DEPARTMENT, departments.getName());
        values.put(KEY_ID_IMAGE_DEPARTMENT, departments.getIdImage());
        // Inserting Row
        db.insert(TABLE_DEPARTMENT, null, values);
        db.close(); // Closing database connection
    }

    // Getting name single department
    public String getDepartment(int id) {
        String name_department = "";
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT + " WHERE " + KEY_ID_DEPARTMENT + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            name_department = cursor.getString(cursor.getColumnIndex(KEY_NAME_DEPARTMENT));
        }
        db.close();
        return name_department;
    }

    // Getting idDepartment single department
    public int getDepartment(String nameDepartment) {
        int id_department = Settings.ID_NULL;
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT + " WHERE " + KEY_NAME_DEPARTMENT + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + nameDepartment});
        if (cursor != null && cursor.moveToFirst()) {
            id_department = cursor.getInt(cursor.getColumnIndex(KEY_ID_DEPARTMENT));
        }
        db.close();
        return id_department;
    }

    // Getting department Count
    public int getDepartmentCount() {
        int count = Settings.COUNT_NULL;
        String countQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null) {
            count = cursor.getCount();
        }
        db.close();
        return count;
    }

    // Getting All Departments
    public List<Departments> getAllDepartments() {
        List<Departments> departmentsList = new ArrayList<Departments>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Departments departments = new Departments(cursor.getInt(cursor.getColumnIndex(KEY_ID_DEPARTMENT)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME_DEPARTMENT)),
                        cursor.getInt(cursor.getColumnIndex(KEY_ID_IMAGE_DEPARTMENT)));
                // Adding departments to list
                departmentsList.add(departments);
            } while (cursor.moveToNext());
        }
        db.close();
        // return departments list
        return departmentsList;
    }

    // Staff
    // Adding new staff
    public void addStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_STAFF, staff.getName());
        values.put(KEY_PLACE_OF_BIRTH_STAFF, staff.getPlaceOfBirth());
        values.put(KEY_BIRTHDAY_STAFF, staff.getBirthday());
        values.put(KEY_PHONE_STAFF, staff.getPhone());
        values.put(KEY_ID_POSITION_IN_COMPANY_STAFF, staff.getIdPositionInCompany());
        values.put(KEY_ID_STATUS_STAFF, staff.getIdStatus());
        values.put(KEY_LEFT_JOB_STAFF, staff.getLeftJob());
        // Inserting Row
        db.insert(TABLE_STAFF, null, values);
        db.close(); // Closing database connection
    }

    // Getting single staff
    public Staff getStaff(int id) {
        Staff staff = null;
        String selectQuery = "SELECT  * FROM " + TABLE_STAFF + " WHERE " + KEY_ID_STAFF + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            staff = new Staff(cursor.getInt(cursor.getColumnIndex(KEY_ID_STAFF)),
                    cursor.getString(cursor.getColumnIndex(KEY_NAME_STAFF)),
                    cursor.getString(cursor.getColumnIndex(KEY_PLACE_OF_BIRTH_STAFF)),
                    cursor.getString(cursor.getColumnIndex(KEY_BIRTHDAY_STAFF)),
                    cursor.getString(cursor.getColumnIndex(KEY_PHONE_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(KEY_ID_POSITION_IN_COMPANY_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(KEY_ID_STATUS_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(KEY_LEFT_JOB_STAFF)));
        }
        db.close();
        return staff;
    }

    // Getting staff Count
    public int getStaffCount() {
        int count = Settings.COUNT_NULL;
        String countQuery = "SELECT  * FROM " + TABLE_STAFF;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        if (cursor != null) {
            count = cursor.getCount();
        }
        db.close();
        return count;
    }

    // Getting All Staffs for Department
    public List<Staff> getAllStaffs(int idDepartment) {
        List<Staff> staffList = new ArrayList<Staff>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STAFF + " WHERE " + KEY_ID_POSITION_IN_COMPANY_STAFF + "=?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + idDepartment});
        // looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor.getInt(cursor.getColumnIndex(KEY_ID_STAFF)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME_STAFF)),
                        cursor.getString(cursor.getColumnIndex(KEY_PLACE_OF_BIRTH_STAFF)),
                        cursor.getString(cursor.getColumnIndex(KEY_BIRTHDAY_STAFF)),
                        cursor.getString(cursor.getColumnIndex(KEY_PHONE_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(KEY_ID_POSITION_IN_COMPANY_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(KEY_ID_STATUS_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(KEY_LEFT_JOB_STAFF)));
                // Adding departments to list
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        // return departments list
        return staffList;
    }

    // Status
    // Adding new status
    public void addStatus(Status status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE_STATUS, status.getTypeStatus());
        // Inserting Row
        db.insert(TABLE_STATUS, null, values);
        db.close(); // Closing database connection
    }

    // Getting type single status
    public String getStatus(int id) {
        String type_status = "";
        String selectQuery = "SELECT  * FROM " + TABLE_STATUS + " WHERE " + KEY_ID_STATUS + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            type_status = cursor.getString(cursor.getColumnIndex(KEY_TYPE_STATUS));
        }
        db.close();
        return type_status;
    }

    // Getting All Status
    public List<Status> getAllStatus() {
        List<Status> statusList = new ArrayList<Status>();
        String selectQuery = "SELECT  * FROM " + TABLE_STATUS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Status status = new Status(cursor.getInt(cursor.getColumnIndex(KEY_ID_STATUS)),
                        cursor.getString(cursor.getColumnIndex(KEY_TYPE_STATUS))
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
        String selectQuery = "SELECT  * FROM " + TABLE_STATUS + " WHERE " + KEY_TYPE_STATUS + "=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + typeStatus});
        if (cursor != null && cursor.moveToFirst()) {
            id_status = cursor.getInt(cursor.getColumnIndex(KEY_ID_STATUS));
        }
        db.close();
        return id_status;
    }
}