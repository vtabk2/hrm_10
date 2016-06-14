package com.example.framgia.hrm_10.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.DBHelper;
import com.example.framgia.hrm_10.controller.DBTest;
import com.example.framgia.hrm_10.controller.DatePickerFragment;
import com.example.framgia.hrm_10.model.Staff;

/**
 * Created by framgia on 09/06/2016.
 */
public class StaffActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mName, mPhone, mPlaceOfBirth;
    private TextView mBirthday, mPositionInCompany, mStatus;
    private Button mSubmit;
    private DBHelper mDbHelper;
    private Bundle mExtras;
    private int mIdStaff;
    private Spinner mSpinnerStatus, mSpinnerPositionInCompany;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        initDbHelper();
        initViews();
        createData();
    }

    private void createData() {
        mExtras = getIntent().getExtras();
        if (mExtras != null) {
            mIdStaff = mExtras.getInt(DBTest.ID_STAFF);
            if (mIdStaff > DBTest.ID_STAFF_NULL) {
                showStaff();
                // enabled text button edit
                setEnableViews(false);
            }
        }
    }

    private void initDbHelper() {
        mDbHelper = new DBHelper(this);
    }

    private void initViews() {
        mExtras = getIntent().getExtras();
        mName = (EditText) findViewById(R.id.edit_name_staff);
        mPlaceOfBirth = (EditText) findViewById(R.id.edit_place_of_birth);
        mPhone = (EditText) findViewById(R.id.edit_phone);
        mSubmit = (Button) findViewById(R.id.button_submit);
        mBirthday = (TextView) findViewById(R.id.text_birthday);
        mPositionInCompany = (TextView) findViewById(R.id.text_position_in_company);
        mStatus = (TextView) findViewById(R.id.text_status);
        mSubmit.setOnClickListener(this);
        mBirthday.setOnClickListener(this);
    }

    private void setEnableViews(boolean enabled) {
        if (enabled) {
            // TODO
        } else {
            mName.setEnabled(false);
            mPositionInCompany.setEnabled(false);
            mBirthday.setEnabled(false);
            mPhone.setEnabled(false);
            mPlaceOfBirth.setEnabled(false);
            mStatus.setEnabled(false);
            mSubmit.setVisibility(View.INVISIBLE);
        }
    }

    private void showStaff() {
        Staff staff = mDbHelper.getStaff(mIdStaff);
        if (staff != null) {
            mName.setText(staff.getName());
            mPlaceOfBirth.setText(staff.getPlaceOfBirth());
            mBirthday.setText(staff.getBirthday());
            mPhone.setText(staff.getPhone());
            mPositionInCompany.setText(mDbHelper.getDepartment(staff.getIdPositionInCompany()));
            mStatus.setText(mDbHelper.getStatus(staff.getIdStatus()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit:
                //TODO
                break;
            case R.id.text_birthday:
                showTimePickerDialog(view);
                break;
        }
    }

    private void showTimePickerDialog(View view) {
        DialogFragment showTime = (new DatePickerFragment()).setBirthday(mBirthday);
        showTime.show(getSupportFragmentManager(), DBTest.DATEPICKER);
    }
}