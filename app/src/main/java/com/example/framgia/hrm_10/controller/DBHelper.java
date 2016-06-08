package com.example.framgia.hrm_10.controller;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.framgia.hrm_10.model.Departments;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by framgia on 02/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final int LOGIN_WITH_ADMIN = 1;
    public static final int LOGIN_NORMAL = 2;
    public static final int LOGIN_FAILED = 0;
    public static final int DATABASE_NULL = 0;
    public static final String ADMIN = "admin";
    // All Static variables
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
    // db test
    public static final String DEPARTMENT1="Departments O1";
    public static final String DEPARTMENT2="Departments O2";
    public static final String DEPARTMENT3="Departments O9";
    public static final String DEPARTMENT4="Departments O5";

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
        // Create tables again
        onCreate(db);
    }
    private void onCreateTable(SQLiteDatabase db) {
        // table department
        String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "("
                + KEY_ID_DEPARTMENT + " INTEGER PRIMARY KEY," + KEY_NAME_DEPARTMENT + " TEXT,"
                + KEY_ID_IMAGE_DEPARTMENT + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_DEPARTMENT);
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
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
    // Getting single department
    public String getDepartment(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DEPARTMENT, new String[]{KEY_ID_DEPARTMENT,
                        KEY_NAME_DEPARTMENT, KEY_ID_IMAGE_DEPARTMENT}, KEY_ID_DEPARTMENT + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        String name_department = cursor.getString(1);
        db.close();
        return name_department;
    }
    // Getting department Count
    public int getDepartmentCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        db.close();
        // return count
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
        if (cursor.moveToFirst()) {
            do {
                Departments departments = new Departments();
                departments.setId(cursor.getInt(0));
                departments.setName(cursor.getString(1));
                departments.setIdImage(cursor.getInt(2));
                // Adding departments to list
                departmentsList.add(departments);
            } while (cursor.moveToNext());
        }
        db.close();
        // return departments list
        return departmentsList;
    }
    // login
    public int login(String name, String pass) {
        return LOGIN_WITH_ADMIN;
    }
}