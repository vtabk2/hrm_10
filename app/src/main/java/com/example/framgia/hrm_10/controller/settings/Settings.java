package com.example.framgia.hrm_10.controller.settings;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.model.data.Account;
import com.example.framgia.hrm_10.model.data.Departments;
import com.example.framgia.hrm_10.model.data.Staff;
import com.example.framgia.hrm_10.model.data.Status;

/**
 * Created by framgia on 09/06/2016.
 */
public class Settings {
    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "staffsManager";
    // Staffs table name
    public static final String TABLE_STAFF = "staff";
    // Department table name
    public static final String TABLE_DEPARTMENT = "department";
    // Status table name
    public static final String TABLE_STATUS = "status";
    // Account table name
    public static final String TABLE_ACCOUNT = "account";
    // DEPARTMENT Table Columns names
    public static final String KEY_ID_DEPARTMENT = "id";
    public static final String KEY_NAME_DEPARTMENT = "name";
    public static final String KEY_ID_IMAGE_DEPARTMENT = "image";
    // STAFF Table Columns names
    public static final String KEY_ID_STAFF = "id";
    public static final String KEY_NAME_STAFF = "name";
    public static final String KEY_PLACE_OF_BIRTH_STAFF = "placeOfBirth";
    public static final String KEY_BIRTHDAY_STAFF = "birthday";
    public static final String KEY_PHONE_STAFF = "phone";
    public static final String KEY_ID_POSITION_IN_COMPANY_STAFF = "idPositionInCompany";
    public static final String KEY_ID_STATUS_STAFF = "idStatus";
    public static final String KEY_LEFT_JOB_STAFF = "letJob";
    // STATUS
    public static final String KEY_ID_STATUS = "id";
    public static final String KEY_TYPE_STATUS = "type";
    // ACCOUNT
    public static final String KEY_ID_ACCOUNT = "id";
    public static final String KEY_NAME_ACCOUNT = "acc";
    public static final String KEY_PASS_ACCOUNT = "pass";
    public static final String KEY_PERMISSION_ACCOUNT = "permission";
    public static final int PERMISSION_DEFAULT = 0;
    // All Static variables
    public static final int DATABASE_NULL = 0;
    public static final int COUNT_NULL = 0;
    public static final int ID_NULL = -1;
    public static final int CHECK_UPDATE_TRUE = 0;
    public static final long CHECK_ADD_TRUE = 0;
    public static final String ID_DEPARTMENT = "iddepartment";
    public static final String ID_STAFF = "idstaff";
    public static final int ID_DEPARTMENT_DEFAULT = 1;
    public static final int ID_STAFF_DEFAULT = 1;
    public static final int ID_DEPARTMENT_NULL = 0;
    public static final int ID_STAFF_NULL = 0;
    public static final String DATE_PICKER = "datePicker";
    public static final String ADD_STAFF = "addStaff";
    public static final String EDIT_STAFF = "editStaff";
    public static final String SHOW_STAFF = "showStaff";
    public static final String SHOW_DEPARTMENT = "showDepartment";
    public static final String SETTINGS = "Settings";
    public static final int LEFT_JOB = 1;
    public static final int NOT_LEFT_JOB = 0;
    public static final int LOGIN_ADMIN = 1;
    public static final int LOGIN_GUEST = 2;
    public static final String EDIT_DEPARTMENT = "editDepartment";
    public static final String ADD_DEPARTMENT = "addDepartment";
    public static final String INTENT_DATA = "data";
    public static final int ID_IMAGE_DEPARTMENT[] = {R.drawable.img_department1,
            R.drawable.img_department2,
            R.drawable.img_department3,
            R.drawable.img_department4,
            R.drawable.img_department5,
            R.drawable.img_department6
    };
    public static final int START_INDEX_DEFAULT = 0;
    // db test
    private static final String DEPARTMENT1 = "DIV 1";
    private static final String DEPARTMENT2 = "DIV 2";
    private static final String DEPARTMENT3 = "DIV 3";
    private static final String DEPARTMENT4 = "Education";
    private static final String STATUS1 = "Trainee";
    private static final String STATUS2 = "Internship";
    private static final String STATUS3 = "Official staff";
    private static final String NAME_STAFF[] = {"Vu Tuan Anh", "Nguyen Van Quang", "Vu Thanh", "Vu Thi Ngoc Anh",
            "Tran Dieu Linh"};
    private static final String PLACE_OF_BIRTH_STAFF[] = {"Ha Noi", "Hai Duong", "Ho Chi Minh", "Da Nang", "Nam Dinh"};
    private static final String BIRTHDAY_STAFF[] = {"29/08/1992", "29/08/1991", "19/08/1989", "29/02/1988", "09/12/1995"};
    private static final String PHONE_STAFF[] = {"0964980253", "0964980253", "0964980253", "0964980253", "0964980253"};
    private static final int ID_POSITION_IN_COMPANY_STAFF[] = {1, 2, 3, 4, 1};
    private static final int ID_STATUS_STAFF[] = {1, 2, 1, 3, 1};
    private static final int LEFT_JOB_STAFF[] = {0, 0, 0, 0, 1};
    private static final String NAME_ACCOUNT[] = {"admin", "test1", "test2", "test3"};
    private static final String PASS_ACCOUNT[] = {"admin", "test1", "test2", "test3"};
    private static final int PERMISSION_ACCOUNT[] = {1, 1, 1, 1};

    public static void create(DBHelper mDbHelper) {
        // db test 7/6/2016
        mDbHelper.getDbDepartment().addDepartment(new Departments(DEPARTMENT1, ID_IMAGE_DEPARTMENT[0]));
        mDbHelper.getDbDepartment().addDepartment(new Departments(DEPARTMENT2, ID_IMAGE_DEPARTMENT[1]));
        mDbHelper.getDbDepartment().addDepartment(new Departments(DEPARTMENT3, ID_IMAGE_DEPARTMENT[2]));
        mDbHelper.getDbDepartment().addDepartment(new Departments(DEPARTMENT4, ID_IMAGE_DEPARTMENT[3]));
        // db test 8/6/2016
        for (int i = 0; i < 60; i++) {
            final int k = i % 5;
            mDbHelper.getDbStaff().addStaff(new Staff(NAME_STAFF[k], PLACE_OF_BIRTH_STAFF[k],
                    BIRTHDAY_STAFF[k], PHONE_STAFF[k],
                    ID_POSITION_IN_COMPANY_STAFF[k],
                    ID_STATUS_STAFF[k], LEFT_JOB_STAFF[k]));
        }
        // db 13/6/2016
        mDbHelper.getDbStatus().addStatus(new Status(STATUS1));
        mDbHelper.getDbStatus().addStatus(new Status(STATUS2));
        mDbHelper.getDbStatus().addStatus(new Status(STATUS3));
        // db 22/6/2016
        for (int i = 0; i < 4; i++) {
            mDbHelper.getDbAccount().addAccount(new Account(NAME_ACCOUNT[i], PASS_ACCOUNT[i], PERMISSION_ACCOUNT[i]));
        }
    }
}
