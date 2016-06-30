package com.example.framgia.hrm_10.controller.settings;

import com.example.framgia.hrm_10.R;

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
    // All Static variables
    public static final int COUNT_NULL = 0;
    public static final int ID_NULL = -1;
    public static final int CHECK_UPDATE_TRUE = 0;
    public static final long CHECK_ADD_TRUE = 0;
    public static final String ID_DEPARTMENT = "iddepartment";
    public static final String ID_STAFF = "idstaff";
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
    public static final String SHARE_PREFERENCES = "sharelogin";
}
