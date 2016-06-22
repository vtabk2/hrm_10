package com.example.framgia.hrm_10.view.editstaffdepartment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Departments;
import com.example.framgia.hrm_10.model.utility.Utility;

/**
 * Created by framgia on 15/06/2016.
 */
public class DepartmentActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageViewDepartment, mImageViewUpdateImageDepartment;
    private EditText mEditTextNameDepartment;
    private Button mButtonSubmit;
    private DBHelper mDbHelper;
    private String mTypeSettings;
    private int mIdImageDepartment;
    private int mIdDepartment;
    private TextView mTextViewUpdateImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department);
        initDBHelper();
        initViews();
        createData();
    }

    private void createData() {
        setEnableViews(true);
        mIdImageDepartment = Settings.ID_DEPARTMENT_DEFAULT;
        switch (mTypeSettings) {
            case Settings.ADDDEPARTMENT:
                // TODO
                break;
            case Settings.EDITDEPARTMENT:
                showDepartment();
                break;
        }
    }

    private void showDepartment() {
        String name = mDbHelper.getDbDepartment().getNameDepartment(mIdDepartment);
        mEditTextNameDepartment.setText(name);
        drawImage();
    }

    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createDbDepartment();
        mTypeSettings = getIntent().getStringExtra(Settings.SETTINGS);
        mIdDepartment = getIntent().getIntExtra(Settings.ID_DEPARTMENT, Settings.ID_STAFF_DEFAULT);
    }

    private void initViews() {
        mImageViewDepartment = (ImageView) findViewById(R.id.image_view_edit_department);
        mImageViewUpdateImageDepartment = (ImageView) findViewById(R.id.image_update_image_department);
        mEditTextNameDepartment = (EditText) findViewById(R.id.edit_editdepartment);
        mButtonSubmit = (Button) findViewById(R.id.button_edit_submit);
        mTextViewUpdateImage = (TextView) findViewById(R.id.text_update_image);
        mButtonSubmit.setOnClickListener(this);
        mImageViewUpdateImageDepartment.setOnClickListener(this);
        mImageViewDepartment.setOnClickListener(this);
        mTextViewUpdateImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_edit_submit:
                checkEditSubmit();
                break;
            case R.id.image_view_edit_department:
            case R.id.text_update_image:
            case R.id.image_update_image_department:
                nextImage();
                break;
        }
    }

    private void checkEditSubmit() {
        switch (mTypeSettings) {
            case Settings.ADDDEPARTMENT:
                showAddDepartment();
                break;
            case Settings.EDITDEPARTMENT:
                showEditDepartment();
                break;
        }
    }

    private void showEditDepartment() {
        if (editAddDepartment(true)) {
            setEnableViews(false);
            Utility.showToast(getApplicationContext(), getText(R.string.updateDepartmSuccessfully));
        } else {
            Utility.showToast(getApplicationContext(), getText(R.string.updateDepartmFailed));
        }
    }

    private void showAddDepartment() {
        if (editAddDepartment(false)) {
            setEnableViews(false);
            Utility.showToast(getApplicationContext(), getText(R.string.addDepartmentSuccessfully));
        } else {
            Utility.showToast(getApplicationContext(), getText(R.string.addDepartmentFailed));
        }
    }

    private void nextImage() {
        if (mIdImageDepartment < Settings.ID_IMAGE_DEPARTMENT.length - 1) {
            mIdImageDepartment++;
        } else {
            mIdImageDepartment = Settings.ID_DEPARTMENT_DEFAULT;
        }
        drawImage();
    }

    private void drawImage() {
        Glide.with(getApplicationContext()).load(Settings.ID_IMAGE_DEPARTMENT[mIdImageDepartment])
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageViewDepartment);
    }

    private void setEnableViews(boolean enabled) {
        mButtonSubmit.setVisibility(enabled ? View.VISIBLE : View.INVISIBLE);
        mEditTextNameDepartment.setEnabled(enabled);
        mImageViewUpdateImageDepartment.setEnabled(enabled);
        mTextViewUpdateImage.setEnabled(enabled);
        mImageViewDepartment.setEnabled(enabled);
    }

    private boolean editAddDepartment(boolean edit) {
        if (mDbHelper.getDbDepartment().checkDepartment(mEditTextNameDepartment.getText().toString(),
                mIdDepartment) && edit) {
            Departments departments = new Departments(mIdDepartment,
                    mEditTextNameDepartment.getText().toString(),
                    Settings.ID_IMAGE_DEPARTMENT[mIdImageDepartment]);
            return mDbHelper.getDbDepartment().updateDepartment(departments);
        } else if (mDbHelper.getDbDepartment().checkDepartment(mEditTextNameDepartment.getText().toString(),
                mIdDepartment) && !edit) {
            Departments departments = new Departments(
                    mEditTextNameDepartment.getText().toString(),
                    Settings.ID_IMAGE_DEPARTMENT[mIdImageDepartment]);
            return mDbHelper.getDbDepartment().addDepartment(departments);
        } else {
            return false;
        }
    }
}
