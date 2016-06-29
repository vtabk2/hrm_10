package com.example.framgia.hrm_10.view.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Account;
import com.example.framgia.hrm_10.model.utility.Utility;
import com.example.framgia.hrm_10.view.showstaffdepartment.ListDepartmentStaffActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditTextName, mEditTextPass;
    private DBHelper mDbHelper;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDbHelper();
        initViews();
        checkAutoLogin();
    }

    private void checkAutoLogin() {
        mSharedPreferences = getSharedPreferences(Settings.SHARE_PREFERENCES, Context.MODE_PRIVATE);
        if (mSharedPreferences.contains(Settings.KEY_ID_ACCOUNT)) {
            nextScreen();
        }
    }

    private void initDbHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createAccount();
    }

    private void initViews() {
        Button signin = (Button) findViewById(R.id.button_login);
        mEditTextName = (EditText) findViewById(R.id.edit_username);
        mEditTextPass = (EditText) findViewById(R.id.edit_password);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String _name = mEditTextName.getText().toString();
        String _pass = mEditTextPass.getText().toString();
        Account account = mDbHelper.getDbAccount().login(_name, _pass);
        if (account != null) {
            onLogin(account);
        } else {
            showLogError();
        }
    }

    private void onLogin(Account account) {
        switch (account.getPermission()) {
            case Settings.LOGIN_ADMIN:
                saveLogin(account);
                Utility.showToast(getApplicationContext(), getText(R.string.login1));
                nextScreen();
                break;
            default:
                showLogError();
                break;
        }
    }

    private void nextScreen() {
        Intent intent = new Intent(getBaseContext(), ListDepartmentStaffActivity.class);
        intent.putExtra(Settings.SETTINGS, Settings.SHOW_DEPARTMENT);
        startActivity(intent);
        finish();
    }

    private void saveLogin(Account account) {
        mSharedPreferences = getSharedPreferences(Settings.SHARE_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(Settings.KEY_ID_ACCOUNT, account.getId());
        editor.commit();
    }

    private void showLogError() {
        Utility.showToast(getApplicationContext(), getText(R.string.login3));
    }
}
