package com.example.framgia.hrm_10.controller;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.model.Departments;
import com.example.framgia.hrm_10.model.Staff;
import com.example.framgia.hrm_10.model.Status;

/**
 * Created by framgia on 09/06/2016.
 */
public class Settings {
    // All Static variables
    public static final int DATABASE_NULL = 0;
    public static final int COUNT_NULL = 0;
    public static final int ID_NULL = -1;
    public static final String ID_DEPARTMENT = "iddepartment";
    public static final String ID_STAFF = "idstaff";
    public static final int ID_DEPARTMENT_NULL = 0;
    public static final int ID_STAFF_NULL = 0;
    public static final String DATEPICKER = "datePicker";
    public static final String ADDSTAFF = "addStaff";
    public static final String EDITSTAFF = "editStaff";
    public static final String SHOWSTAFF = "showStaff";
    public static final String SETTINGS = "Settings";
    public static final int LEFT_JOB = 1;
    public static final int NOT_LEFT_JOB = 0;
    // db test
    private static final String DEPARTMENT1 = "Departments O1";
    private static final String DEPARTMENT2 = "Departments O2";
    private static final String DEPARTMENT3 = "Departments O9";
    private static final String DEPARTMENT4 = "Education";
    private static final String STATUS1 = "Trainee";
    private static final String STATUS2 = "Internship";
    private static final String STATUS3 = "Official staff";
    private static final String NAME_STAFF[] = {"Vu Tuan Anh", "Nguyen Van Quang", "Vu Thanh", "Vu Thi Ngoc Anh",
            "Tran Dieu Linh"};
    private static final String PLACE_OF_BIRTH_STAFF[] = {"Ha Noi", "Hai Duong", "Ho Chi Minh", "Da Nang", "Nam Dinh"};
    private static final String BIRTHDAY_STAFF[] = {"29/08/1992", "29/08/1991", "19/08/1989", "29/02/1988", "09/12/1995"};
    private static final String PHONE_STAFF[] = {"0964980253", "0964980253", "0964980253", "0964980253", "0964980253"};
    private static final int ID_POSITION_IN_COMPANY_STAFF[] = {1, 1, 1, 1, 1};
    private static final int ID_STATUS_STAFF[] = {1, 2, 1, 3, 1};
    private static final int LEFT_JOB_STAFF[] = {NOT_LEFT_JOB, NOT_LEFT_JOB, LEFT_JOB, NOT_LEFT_JOB, LEFT_JOB};

    public static void create(DBHelper mDbHelper) {
        // db test 7/6/2016
        mDbHelper.addDepartment(new Departments(DEPARTMENT1, R.drawable.img_department1));
        mDbHelper.addDepartment(new Departments(DEPARTMENT2, R.drawable.img_department2));
        mDbHelper.addDepartment(new Departments(DEPARTMENT3, R.drawable.img_department3));
        mDbHelper.addDepartment(new Departments(DEPARTMENT4, R.drawable.img_department4));
        // db test 8/6/2016
        for (int i = 0; i < 60; i++) {
            final int k = i % 5;
            mDbHelper.addStaff(new Staff(NAME_STAFF[k], PLACE_OF_BIRTH_STAFF[k],
                    BIRTHDAY_STAFF[k], PHONE_STAFF[k],
                    ID_POSITION_IN_COMPANY_STAFF[k],
                    ID_STATUS_STAFF[k], LEFT_JOB_STAFF[k]));
        }
        // db 13/6/2016
        mDbHelper.addStatus(new Status(STATUS1));
        mDbHelper.addStatus(new Status(STATUS2));
        mDbHelper.addStatus(new Status(STATUS3));
    }
}
