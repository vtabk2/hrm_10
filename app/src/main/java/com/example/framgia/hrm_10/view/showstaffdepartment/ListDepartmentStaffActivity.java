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
import com.example.framgia.hrm_10.view.editstaffdepartment.DepartmentActivity;
import com.example.framgia.hrm_10.view.editstaffdepartment.StaffActivity;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by framgia on 02/06/2016.
 */
public class ListDepartmentStaffActivity extends AppCompatActivity implements OnClickItemListener {
    private DBHelper mDbHelper;
    private List<Departments> mDepartmentsList;
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
        String settings = getIntent().getStringExtra(Settings.SETTINGS);
        switch (settings) {
            case Settings.SHOWDEPARTMENT:
                initViews(DataRecyclerViewAdapter.TYPE_DEPARTMENT);
                break;
            case Settings.SHOWSTAFF:
                mIdDepartment = getIntent().getIntExtra(Settings.ID_DEPARTMENT, Settings.ID_DEPARTMENT_DEFAULT);
                if (mIdDepartment > Settings.ID_DEPARTMENT_NULL) {
                    mStaffList = mDbHelper.getDbStaff().getAllStaffs(mIdDepartment);
                    initViews(DataRecyclerViewAdapter.TYPE_STAFF);
                }
                break;
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
                Intent intent = new Intent(getApplicationContext(), ListDepartmentStaffActivity.class);
                intent.putExtra(Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.SHOWSTAFF);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.SHOWSTAFF);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onLongClickItem(View view, int position) {
        int type = mAdapter.getItemViewType(position);
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                Intent intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                intent.putExtra(Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.EDITDEPARTMENT);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                intent = new Intent(getApplicationContext(), StaffActivity.class);
                intent.putExtra(Settings.ID_STAFF, mStaffList.get(position).getId());
                intent.putExtra(Settings.SETTINGS, Settings.EDITSTAFF);
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
        switch (id) {
            case R.id.addStaff:
                Intent intent = new Intent(getBaseContext(), StaffActivity.class);
                intent.putExtra(Settings.SETTINGS, Settings.ADDSTAFF);
                startActivity(intent);
                return true;
            case R.id.addDepartment:
                intent = new Intent(getApplicationContext(), DepartmentActivity.class);
                intent.putExtra(Settings.SETTINGS, Settings.ADDDEPARTMENT);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}