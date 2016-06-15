package com.example.framgia.hrm_10.view.showstaffdepartment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.framgia.hrm_10.R;
import com.example.framgia.hrm_10.controller.database.DBHelper;
import com.example.framgia.hrm_10.controller.recyclerviewdata.DataRecyclerViewAdapter;
import com.example.framgia.hrm_10.controller.settings.Settings;
import com.example.framgia.hrm_10.model.data.Departments;
import com.example.framgia.hrm_10.model.data.Staff;
import com.example.framgia.hrm_10.model.listenner.OnClickItemListener;
import com.example.framgia.hrm_10.view.editstaffdepartment.StaffActivity;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by framgia on 02/06/2016.
 */
public class ListDepartmentStaffActivity extends AppCompatActivity implements OnClickItemListener {
    private DBHelper mDbHelper;
    private List<Departments> mDepartmentsList;
    private Bundle mBundle;
    private List<Staff> mStaffList;
    private RecyclerView mRecyclerView;
    private DataRecyclerViewAdapter mAdapter;
    private boolean mIscreated;
    private int mTypeDataRecyclerViewAdapter;
    private int mIdDepartment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department_staff);
        initDBHelper();
        checkInitViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIscreated = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIscreated) {
            showUpdateViews();
        }
    }

    private void showUpdateViews() {
        switch (mTypeDataRecyclerViewAdapter) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                mDepartmentsList = mDbHelper.getDbDepartment().getAllDepartments();
                mAdapter = new DataRecyclerViewAdapter(mDepartmentsList);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                mStaffList = mDbHelper.getDbStaff().getAllStaffs(mIdDepartment);
                mAdapter = new DataRecyclerViewAdapter(mStaffList, DataRecyclerViewAdapter.TYPE_STAFF);
                break;
        }
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickItemListener(this);
    }

    private void checkInitViews() {
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mIdDepartment = mBundle.getInt(Settings.ID_DEPARTMENT);
            if (mIdDepartment > Settings.ID_DEPARTMENT_NULL) {
                mStaffList = mDbHelper.getDbStaff().getAllStaffs(mIdDepartment);
                initViews(DataRecyclerViewAdapter.TYPE_STAFF);
            }
        } else {
            initViews(DataRecyclerViewAdapter.TYPE_DEPARTMENT);
        }
    }

    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        mDbHelper.createDbDepartment();
        mDbHelper.createDbStaff();
        int length;
        length = mDbHelper.getDbDepartment().getDepartmentCount();
        if (length == Settings.DATABASE_NULL) {
            Settings.create(mDbHelper);
        }
        mDepartmentsList = mDbHelper.getDbDepartment().getAllDepartments();
    }

    private void initViews(final int type) {
        mTypeDataRecyclerViewAdapter = type;
        mAdapter = null;
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                mAdapter = new DataRecyclerViewAdapter(mDepartmentsList);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                mAdapter = new DataRecyclerViewAdapter(mStaffList, type);
                break;
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickItemListener(this);
    }

    @Override
    public void onClickItem(View view, int position) {
        switch (mAdapter.getItemViewType(position)) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt(Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                Intent intent = new Intent(getApplicationContext(), ListDepartmentStaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                dataBundle = new Bundle();
                dataBundle.putInt(Settings.ID_STAFF, mStaffList.get(position).getId());
                dataBundle.putString(Settings.SETTINGS, Settings.SHOWSTAFF);
                intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLongClickItem(View view, int position) {
        int type = mAdapter.getItemViewType(position);
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                // TODO
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                Bundle dataBundle1 = new Bundle();
                dataBundle1.putInt(Settings.ID_STAFF, mStaffList.get(position).getId());
                dataBundle1.putString(Settings.SETTINGS, Settings.EDITSTAFF);
                Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtras(dataBundle1);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Bundle dataBundle = new Bundle();
        switch (id) {
            case R.id.addStaff:
                dataBundle.putString(Settings.SETTINGS, Settings.ADDSTAFF);
                Intent intent = new Intent(getBaseContext(), StaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                return true;
            case R.id.addDepartment:
                // TODO
                return true;
            default:
                return false;
        }
    }
}