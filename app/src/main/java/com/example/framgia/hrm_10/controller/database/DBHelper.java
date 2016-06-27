package com.example.framgia.hrm_10.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.framgia.hrm_10.controller.settings.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by framgia on 02/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private DBDepartment mDbDepartment;
    private DBStaff mDbStaff;
    private DBStatus mDbStatus;
    private DBAccount mDbAccount;
    private Context mContext;
    private String mDatabasePath = "";
    private String mDatabaseName = "staffsManager";
    private String DATA = "/data/data/";
    private String DATABASE = "/databases/";

    public DBHelper(Context context) {
        super(context, Settings.DATABASE_NAME, null, Settings.DATABASE_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            mDatabasePath = context.getApplicationInfo().dataDir + DATABASE;
        } else {
            mDatabasePath = DATA + context.getPackageName() + DATABASE;
        }
        this.mContext = context;
        try {
            createDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToCreateDatabase");
        }
    }

    public void createDataBase() throws IOException {
        if (!isDatabaseExisted()) {
            this.getReadableDatabase();
            this.close();
            copyDataBase();
        }
    }

    private boolean isDatabaseExisted() {
        File dbFile = new File(mDatabasePath + mDatabaseName);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream input = mContext.getAssets().open(mDatabaseName);
        String outFileName = mDatabasePath + mDatabaseName;
        OutputStream output = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
}