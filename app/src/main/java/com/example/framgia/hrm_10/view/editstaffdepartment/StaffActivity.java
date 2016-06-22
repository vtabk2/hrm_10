package com.example.framgia.hrm_10.view.editstaffdepartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.controller.time.DatePickerFragment;
import com.example.framgia.hrm_10.model.data.Departments;
import com.example.framgia.hrm_10.model.data.Staff;
import com.example.framgia.hrm_10.model.data.Status;
import com.example.framgia.hrm_10.model.utility.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by framgia on 09/06/2016.
 */
public class StaffActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditTextName, mEditTextPhone, mEditTextPlaceOfBirth;
    private TextView mTextViewBirthday, mTextViewPositionInCompany, mTextViewStatus;
    private Button mButtonSubmit;
    private DBHelper mDbHelper;
    private int mIdStaff;
    private Spinner mSpinnerStatus, mSpinnerPositionInCompany;
    private RadioGroup mRadioGroupLeftJob;
    private RadioButton mRadioButtonYes, mRadioButtonNo;
    private String mTypeSettings;
    private List<String> mStatusList = new ArrayList<String>();
    private List<String> mPositionInCompanyList = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        initDbHelper();
        initViews();
        createData();
    }

    private void createData() {
        mTypeSettings = getIntent().getStringExtra(Settings.SETTINGS);
        mIdStaff = getIntent().getIntExtra(Settings.ID_STAFF, Settings.ID_STAFF_DEFAULT);
        switch (mTypeSettings) {
            case Settings.ADD_STAFF:
                setEnableViews(true, true);
                setOnItemSelectedListener();
                break;
            case Settings.EDIT_STAFF:
                setEnableViews(true, false);
                setOnItemSelectedListener();
                showStaff();
                break;
            case Settings.SHOW_STAFF:
                showStaff();
                setEnableViews(false, false);
                break;
        }
    }

    private void initDbHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createDbDepartment();
        mDbHelper.createDbStaff();
        mDbHelper.createDbStatus();
        List<Status> statusList = mDbHelper.getDbStatus().getAllStatus();
        for (Status status : statusList) {
            mStatusList.add(status.getTypeStatus());
        }
        List<Departments> departmentsList = mDbHelper.getDbDepartment().getAllDepartments();
        for (Departments departments : departmentsList) {
            mPositionInCompanyList.add(departments.getName());
        }
    }

    private void initViews() {
        mEditTextName = (EditText) findViewById(R.id.edit_name_staff);
        mEditTextPlaceOfBirth = (EditText) findViewById(R.id.edit_place_of_birth);
        mEditTextPhone = (EditText) findViewById(R.id.edit_phone);
        mButtonSubmit = (Button) findViewById(R.id.button_submit);
        mTextViewBirthday = (TextView) findViewById(R.id.text_birthday);
        mTextViewPositionInCompany = (TextView) findViewById(R.id.text_position_in_company);
        mTextViewStatus = (TextView) findViewById(R.id.text_status);
        mButtonSubmit.setOnClickListener(this);
        mTextViewBirthday.setOnClickListener(this);
        createSpinnerViews();
        mRadioGroupLeftJob = (RadioGroup) findViewById(R.id.radioGroup_Left_Job);
        mRadioButtonYes = (RadioButton) findViewById(R.id.radioButton_Yes);
        mRadioButtonNo = (RadioButton) findViewById(R.id.radioButton_No);
    }

    private void setOnItemSelectedListener() {
        mSpinnerPositionInCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewPositionInCompany.setText(mPositionInCompanyList.get((int) parent.getItemIdAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO
            }
        });
        mSpinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextViewStatus.setText(mStatusList.get((int) parent.getItemIdAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO
            }
        });
    }

    private void createSpinnerViews() {
        // SpinnerPositionInCompany
        mSpinnerPositionInCompany = (Spinner) findViewById(R.id.spinner_position_in_company);
        ArrayAdapter<String> adapterPositionInCompany = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        mPositionInCompanyList
                );
        adapterPositionInCompany.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpinnerPositionInCompany.setAdapter(adapterPositionInCompany);
        // SpinnerStatus
        mSpinnerStatus = (Spinner) findViewById(R.id.spinner_status);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        mStatusList
                );
        adapterStatus.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        mSpinnerStatus.setAdapter(adapterStatus);
    }

    private void setEnableViews(boolean enabled, boolean add) {
        mEditTextName.setEnabled(enabled);
        mEditTextPlaceOfBirth.setEnabled(enabled);
        mTextViewBirthday.setEnabled(enabled);
        mEditTextPhone.setEnabled(enabled);
        mTextViewPositionInCompany.setEnabled(enabled);
        mTextViewStatus.setEnabled(enabled);
        mEditTextName.setFocusableInTouchMode(enabled);
        mEditTextName.setClickable(enabled);
        mRadioButtonYes.setEnabled(enabled);
        mRadioButtonNo.setEnabled(enabled);
        mButtonSubmit.setVisibility(enabled ? View.VISIBLE : View.INVISIBLE);
        mSpinnerPositionInCompany.setVisibility(enabled ? View.VISIBLE : View.INVISIBLE);
        mSpinnerStatus.setVisibility(enabled ? View.VISIBLE : View.INVISIBLE);
        mTextViewPositionInCompany.setVisibility(enabled ? View.INVISIBLE : View.VISIBLE);
        mTextViewStatus.setVisibility(enabled ? View.INVISIBLE : View.VISIBLE);
        if (add) {
            mTextViewBirthday.setText(R.string.birthdayDefault);
            mRadioButtonNo.setChecked(add);
        }
    }

    private void showStaff() {
        if (mIdStaff > Settings.ID_STAFF_NULL) {
            Staff staff = mDbHelper.getDbStaff().getStaff(mIdStaff);
            if (staff != null) {
                mEditTextName.setText(staff.getName());
                mEditTextPlaceOfBirth.setText(staff.getPlaceOfBirth());
                mTextViewBirthday.setText(staff.getBirthday());
                mEditTextPhone.setText(staff.getPhone());
                mTextViewPositionInCompany.setText(mDbHelper.getDbDepartment().getNameDepartment(staff.getIdPositionInCompany()));
                mTextViewStatus.setText(mDbHelper.getDbStatus().getStatus(staff.getIdStatus()));
                mSpinnerPositionInCompany.setSelection(staff.getIdPositionInCompany() - 1);
                mSpinnerStatus.setSelection(staff.getIdStatus() - 1);
                mRadioButtonYes.setChecked(staff.getLeftJob() == Settings.LEFT_JOB);
                mRadioButtonNo.setChecked(staff.getLeftJob() == Settings.NOT_LEFT_JOB);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_submit:
                checkSubmit();
                break;
            case R.id.text_birthday:
                showTimePickerDialog(view);
                break;
        }
    }

    private void checkSubmit() {
        switch (mTypeSettings) {
            case Settings.ADD_STAFF:
                showAddStaff();
                break;
            case Settings.EDIT_STAFF:
                showEditStaff();
                break;
        }
    }

    private void showEditStaff() {
        if (editStaff()) {
            setEnableViews(false, false);
            Utility.showToast(getApplicationContext(), getText(R.string.editSuccessfully));
        } else {
            Utility.showToast(getApplicationContext(), getText(R.string.editFailed));
        }
    }

    private void showAddStaff() {
        if (addStaff()) {
            setEnableViews(false, false);
            Utility.showToast(getApplicationContext(), getText(R.string.addSuccessfully));
        } else {
            Utility.showToast(getApplicationContext(), getText(R.string.addFailed));
        }
    }

    private int checkLeftJob() {
        int idChecked = mRadioGroupLeftJob.getCheckedRadioButtonId();
        return idChecked == R.id.radioButton_Yes ? Settings.LEFT_JOB : Settings.NOT_LEFT_JOB;
    }

    private boolean editStaff() {
        if (checkStaffNull()) {
            Staff staff = new Staff(mIdStaff, mEditTextName.getText().toString(),
                    mEditTextPlaceOfBirth.getText().toString(),
                    mTextViewBirthday.getText().toString(),
                    mEditTextPhone.getText().toString(),
                    mDbHelper.getDbDepartment().getIdDepartment(mTextViewPositionInCompany.getText().toString()),
                    mDbHelper.getDbStatus().getStatus(mTextViewStatus.getText().toString()),
                    checkLeftJob());
            return mDbHelper.getDbStaff().updateStaff(staff);
        }
        return false;
    }

    private boolean addStaff() {
        if (checkStaffNull()) {
            Staff staff = new Staff(mEditTextName.getText().toString(),
                    mEditTextPlaceOfBirth.getText().toString(),
                    mTextViewBirthday.getText().toString(),
                    mEditTextPhone.getText().toString(),
                    mDbHelper.getDbDepartment().getIdDepartment(mTextViewPositionInCompany.getText().toString()),
                    mDbHelper.getDbStatus().getStatus(mTextViewStatus.getText().toString()), checkLeftJob());
            mDbHelper.getDbStaff().addStaff(staff);
            return true;
        }
        return false;
    }

    private boolean checkStaffNull() {
        if (!TextUtils.isEmpty(mEditTextName.getText().toString())
                && !TextUtils.isEmpty(mEditTextPlaceOfBirth.getText().toString())
                && !TextUtils.equals(mTextViewBirthday.getText().toString(), getText(R.string.birthdayDefault))
                && !TextUtils.isEmpty(mEditTextPhone.getText().toString())) {
            return true;
        }
        return false;
    }

    private void showTimePickerDialog(View view) {
        DialogFragment showTime = (new DatePickerFragment()).setBirthday(mTextViewBirthday);
        showTime.show(getSupportFragmentManager(), Settings.DATE_PICKER);
    }
}