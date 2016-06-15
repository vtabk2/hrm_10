package com.example.framgia.hrm_10.view;

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
import com.example.framgia.hrm_10.controller.DBHelper;
import com.example.framgia.hrm_10.controller.DataRecyclerViewAdapter;
import com.example.framgia.hrm_10.controller.Settings;
import com.example.framgia.hrm_10.model.Departments;
import com.example.framgia.hrm_10.model.OnClickItemListener;
import com.example.framgia.hrm_10.model.Staff;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by framgia on 02/06/2016.
 */
public class ListDepartmentStaffActivity extends AppCompatActivity implements OnClickItemListener {
    private DBHelper mDbHelper;
    private List<Departments> mDepartmentsList;
    private Bundle mBundle;
    private List<Staff> mListStaff;
    private RecyclerView mRecyclerView;
    private DataRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department_staff);
        initDBHelper();
        checkInitViews();
    }

    private void checkInitViews() {
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            int value = mBundle.getInt(Settings.ID_DEPARTMENT);
            if (value > Settings.ID_DEPARTMENT_NULL) {
                mListStaff = mDbHelper.getAllStaffs(value);
                initViews(DataRecyclerViewAdapter.TYPE_STAFF);
            }
        } else {
            initViews(DataRecyclerViewAdapter.TYPE_DEPARTMENT);
        }
    }

    private void initDBHelper() {
        mDbHelper = new DBHelper(this);
        int length;
        length = mDbHelper.getDepartmentCount();
        if (length == Settings.DATABASE_NULL) {
            // create db test
            Settings.create(mDbHelper);
        }
        mDepartmentsList = mDbHelper.getAllDepartments();
    }

    private void initViews(final int type) {
        mAdapter = null;
        switch (type) {
            case DataRecyclerViewAdapter.TYPE_DEPARTMENT:
                mAdapter = new DataRecyclerViewAdapter(mDepartmentsList);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                mAdapter = new DataRecyclerViewAdapter(mListStaff, type);
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
                dataBundle.putInt("" + Settings.ID_DEPARTMENT, mDepartmentsList.get(position).getId());
                Intent intent = new Intent(getApplicationContext(), ListDepartmentStaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                break;
            case DataRecyclerViewAdapter.TYPE_STAFF:
                dataBundle = new Bundle();
                dataBundle.putInt("" + Settings.ID_STAFF, mListStaff.get(position).getId());
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
        if (type == DataRecyclerViewAdapter.TYPE_STAFF) {
            Bundle dataBundle = new Bundle();
            dataBundle = new Bundle();
            dataBundle.putInt("" + Settings.ID_STAFF, mListStaff.get(position).getId());
            dataBundle.putString(Settings.SETTINGS, Settings.EDITSTAFF);
            Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
            intent.putExtras(dataBundle);
            startActivity(intent);
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
                Bundle dataBundle = new Bundle();
                dataBundle.putString(Settings.SETTINGS, Settings.ADDSTAFF);
                Intent intent = new Intent(getBaseContext(), StaffActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}