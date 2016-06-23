package com.example.framgia.hrm_10.controller.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 20/06/2016.
 */
public class DBStaff {
    private SQLiteOpenHelper mSqLiteOpenHelper;

    public DBStaff(SQLiteOpenHelper sqLiteOpenHelper) {
        this.mSqLiteOpenHelper = sqLiteOpenHelper;
    }

    // Adding new staff
    public void addStaff(Staff staff) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_NAME_STAFF, staff.getName());
        values.put(Settings.KEY_PLACE_OF_BIRTH_STAFF, staff.getPlaceOfBirth());
        values.put(Settings.KEY_BIRTHDAY_STAFF, staff.getBirthday());
        values.put(Settings.KEY_PHONE_STAFF, staff.getPhone());
        values.put(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF, staff.getIdPositionInCompany());
        values.put(Settings.KEY_ID_STATUS_STAFF, staff.getIdStatus());
        values.put(Settings.KEY_LEFT_JOB_STAFF, staff.getLeftJob());
        db.insert(Settings.TABLE_STAFF, null, values);
        db.close();
    }

    // Updating single staff
    public boolean updateStaff(Staff staff) {
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.KEY_NAME_STAFF, staff.getName());
        values.put(Settings.KEY_PLACE_OF_BIRTH_STAFF, staff.getPlaceOfBirth());
        values.put(Settings.KEY_BIRTHDAY_STAFF, staff.getBirthday());
        values.put(Settings.KEY_PHONE_STAFF, staff.getPhone());
        values.put(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF, staff.getIdPositionInCompany());
        values.put(Settings.KEY_ID_STATUS_STAFF, staff.getIdStatus());
        values.put(Settings.KEY_LEFT_JOB_STAFF, staff.getLeftJob());
        int checkupdate = db.update(Settings.TABLE_STAFF, values, Settings.KEY_ID_STAFF + " = ?",
                new String[]{String.valueOf(staff.getId())});
        db.close();
        return checkupdate >= Settings.CHECK_ADD_TRUE;
    }

    // Getting single staff
    public Staff getStaff(int id) {
        Staff staff = null;
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_STAFF + " WHERE " + Settings.KEY_ID_STAFF + "=?";
        SQLiteDatabase db = mSqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + id});
        if (cursor != null && cursor.moveToFirst()) {
            staff = new Staff(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STAFF)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_STAFF)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_PLACE_OF_BIRTH_STAFF)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_BIRTHDAY_STAFF)),
                    cursor.getString(cursor.getColumnIndex(Settings.KEY_PHONE_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS_STAFF)),
                    cursor.getInt(cursor.getColumnIndex(Settings.KEY_LEFT_JOB_STAFF)));
        }
        db.close();
        return staff;
    }

    // Getting List staff for Department by Id
    public List<Staff> getListStaffbyIdDepartment(int start, int idDepartment, int limit) {
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuery = "SELECT  * FROM " + Settings.TABLE_STAFF
                + " WHERE " + Settings.KEY_ID_POSITION_IN_COMPANY_STAFF + "=?"
                + " ORDER BY " + Settings.KEY_NAME_STAFF
                + " LIMIT " + limit
                + " OFFSET " + start;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"" + idDepartment});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PLACE_OF_BIRTH_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_BIRTHDAY_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PHONE_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_LEFT_JOB_STAFF)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return staffList;
    }

    // Getting All Staffs for Department
    public List<Staff> getListStaffByName(int start, String nameStaff, int limit) {
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuery = "SELECT * FROM " + Settings.TABLE_STAFF
                + " WHERE " + Settings.KEY_NAME_STAFF
                + " like '%" + nameStaff + "%'"
                + " ORDER BY " + Settings.KEY_NAME_STAFF
                + " LIMIT " + limit
                + " OFFSET " + start;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PLACE_OF_BIRTH_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_BIRTHDAY_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PHONE_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_LEFT_JOB_STAFF)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return staffList;
    }

    // Getting All Staffs for Department
    public List<Staff> getListStaffByPhone(int start, String phone, int limit) {
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuery = "SELECT * FROM " + Settings.TABLE_STAFF
                + " WHERE " + Settings.KEY_PHONE_STAFF
                + " like '%" + phone + "%'"
                + " ORDER BY " + Settings.KEY_NAME_STAFF
                + " LIMIT " + limit
                + " OFFSET " + start;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PLACE_OF_BIRTH_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_BIRTHDAY_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PHONE_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_LEFT_JOB_STAFF)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return staffList;
    }

    // Getting All Staffs for Department
    public List<Staff> getListStaffByDepartment(int start, String nameDepartment, int limit) {
        List<Staff> staffList = new ArrayList<Staff>();
        String selectQuery = "SELECT "
                + Settings.TABLE_STAFF + "." + Settings.KEY_ID_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_NAME_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_PLACE_OF_BIRTH_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_BIRTHDAY_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_PHONE_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_ID_POSITION_IN_COMPANY_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_ID_STATUS_STAFF + ","
                + Settings.TABLE_STAFF + "." + Settings.KEY_LEFT_JOB_STAFF
                + " FROM " + Settings.TABLE_STAFF + "," + Settings.TABLE_DEPARTMENT
                + " WHERE " + Settings.TABLE_STAFF + "." + Settings.KEY_ID_POSITION_IN_COMPANY_STAFF + "=" + Settings.TABLE_DEPARTMENT + "." + Settings.KEY_ID_DEPARTMENT
                + " AND " + Settings.TABLE_DEPARTMENT + "." + Settings.KEY_NAME_DEPARTMENT
                + " like '%" + nameDepartment + "%'"
                + " ORDER BY " + Settings.TABLE_STAFF + "." + Settings.KEY_NAME_STAFF
                + " LIMIT " + limit
                + " OFFSET " + start;
        SQLiteDatabase db = mSqLiteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Staff staff = new Staff(cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_NAME_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PLACE_OF_BIRTH_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_BIRTHDAY_STAFF)),
                        cursor.getString(cursor.getColumnIndex(Settings.KEY_PHONE_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_POSITION_IN_COMPANY_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_ID_STATUS_STAFF)),
                        cursor.getInt(cursor.getColumnIndex(Settings.KEY_LEFT_JOB_STAFF)));
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return staffList;
    }
}
