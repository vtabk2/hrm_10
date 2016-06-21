package com.example.framgia.hrm_10.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.utility.Utility;
import com.example.framgia.hrm_10.view.showstaffdepartment.ListDepartmentStaffActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonSignin;
    private EditText mEditTextName, mEditTextPass;
    private TextView mTextViewCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mButtonSignin = (Button) findViewById(R.id.button_login);
        mEditTextName = (EditText) findViewById(R.id.edit_username);
        mEditTextPass = (EditText) findViewById(R.id.edit_password);
        mTextViewCreate = (TextView) findViewById(R.id.text_create);
        mTextViewCreate.setVisibility(View.GONE);
        mButtonSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String _name = mEditTextName.getText().toString();
        String _pass = mEditTextPass.getText().toString();
        Utility.showToast(getApplicationContext(), getText(R.string.login1));
        Intent intent = new Intent(getBaseContext(), ListDepartmentStaffActivity.class);
        intent.putExtra(Settings.SETTINGS, Settings.SHOWDEPARTMENT);
        startActivity(intent);
    }
}
