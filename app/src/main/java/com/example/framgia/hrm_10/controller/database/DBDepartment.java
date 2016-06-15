package com.example.framgia.hrm_10.controller.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Departments;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 20/06/2016.
 */
public class DBDepartment {
    private SQLiteOpenHelper mSqLiteOpenHelper;

    public DBDepartment(SQLiteOpenHelper sqLiteOpenHelper) {
        this.mSqLiteOpenHelper = sqLiteOpenHelper;
    }

    // Adding new department
    public boolean addDepartment(Departments departments) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_NAME_DEPARTMENT, departments.getName());
        values.put(Settings.KEY_ID_IMAGE_DEPARTMENT, departments.getIdImage());
        long check = db.insert(Settings.TABLE_DEPARTMENT, null, values);
        db.close();
        return check >= Settings.CHECK_ADD_TRUE;
    }

    // Updating single department
    public boolean updateDepartment(Departments departments) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_NAME_DEPARTMENT, departments.getName());
        values.put(Settings.KEY_ID_IMAGE_DEPARTMENT, departments.getIdImage());
        int checkupdate = db.update(Settings.TABLE_DEPARTMENT, values, Settings.KEY_ID_DEPARTMENT + " = ?",
                new String[]{String.valueOf(departments.getId())});
        db.close();
        return checkupdate >= Settings.CHECK_UPDATE_TRUE;
    }

    // Getting name single department
    public String getNameDepartment(int id) {
        String name_department = "";
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_DEPARTMENT + " WHERE " + Settings.KEY_ID_DEPARTMENT + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            name_department = cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_DEPARTMENT));
        }
        db.close();
        return name_department;
    }

    // Getting name single department
    public Departments getDepartment(int id) {
        Departments departments = null;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_DEPARTMENT + " WHERE " + Settings.KEY_ID_DEPARTMENT + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            departments = new Departments(cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_DEPARTMENT)),
                    cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_IMAGE_DEPARTMENT)));
        }
        db.close();
        return departments;
    }

    // Getting idDepartment single department
    public int getIdDepartment(String nameDepartment) {
        int id_department = Settings.ID_NULL;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_DEPARTMENT + " WHERE " + Settings.KEY_NAME_DEPARTMENT + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + nameDepartment});
        if (cursor != null && cursor.moveToFirst()) {
            id_department = cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_DEPARTMENT));
        }
        db.close();
        return id_department;
    }

    // Getting department Count
    public int getDepartmentCount() {
        int count = Settings.COUNT_NULL;
        String countQuery = "SELECT  * FROM " + Settings.TABLE_DEPARTMENT;
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
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
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_DEPARTMENT;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Departments departments = new Departments(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_DEPARTMENT)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_DEPARTMENT)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_IMAGE_DEPARTMENT)));
                departmentsList.add(departments);
            } while (cursor.moveToNext());
        }
        db.close();
        return departmentsList;
    }

    public boolean checkDepartment(String nameNew, int idDepartment) {
        if (TextUtils.isEmpty(nameNew)) {
            return false;
        }
        return checkNameNew(nameNew, idDepartment);
    }

    private boolean checkNameNew(String nameNew, int idDepartment) {
        String selectQuery = "SELECT * FROM " + Settings.TABLE_DEPARTMENT + " WHERE " + Settings.KEY_NAME_DEPARTMENT + " = ?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{nameNew});
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_DEPARTMENT));
            if (id != idDepartment) {
                return false;
            }
        }
        return true;
    }
}
